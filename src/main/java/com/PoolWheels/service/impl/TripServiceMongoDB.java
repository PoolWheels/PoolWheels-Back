package com.PoolWheels.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.PoolWheels.dto.TripDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.PoolWheels.entities.Trip;
import com.PoolWheels.repository.TripRepository;
import com.PoolWheels.repository.UserDriverRepository;
import com.PoolWheels.repository.UserTravelerRepository;
import com.PoolWheels.service.TripService;
import com.mongodb.DuplicateKeyException;

@Service
public class TripServiceMongoDB implements TripService{

    private final TripRepository tripsRepository;
    private final UserDriverRepository driverRepository;
    private final UserTravelerRepository travelerRepository;

    public TripServiceMongoDB ( @Autowired TripRepository tripsRepository, @Autowired UserDriverRepository driverRepository, @Autowired UserTravelerRepository travelerRepository ){
        this.tripsRepository = tripsRepository;
        this.travelerRepository = travelerRepository;
        this.driverRepository = driverRepository;
    }

    /**
     * It creates a new trip and saves it to the database
     * 
     * @param trip The trip object that is being created.
     * @return The trip object is being returned.
     */
    @Override
    public Trip create(Trip trip) {
        try{
        tripsRepository.insert(trip);
        Optional<Trip> tripTemp = tripsRepository.findById(trip.getId());
        return tripTemp.orElse(null);
        } catch (DuplicateKeyException e){
            System.out.println("The specified id is already registered");
            return null;
        }
    }

    /**
     * It returns a trip object from the database by its id
     * 
     * @param id The id of the trip to be deleted.
     * @return The method is returning the trip object that is found by the id.
     */
    @Override
    public Trip findById(String id) {
        return tripsRepository.findById(id).get();
    }

    /**
     * This function returns a list of all trips in the database.
     * 
     * @return A list of trips
     */
    @Override
    public List<Trip> getAll() {
        return tripsRepository.findAll();
    }

    /**
     * It deletes a trip by its id
     * 
     * @param id The id of the trip to be deleted.
     * @return 
     */
    @Override
    public boolean deleteById(String id) {
        tripsRepository.deleteById(id);
        return true;
    }

    /**
     * // Java
     * @Override
     *     public Trips update(Trips trip, String id) {
     *         create(trip);
     *         return findById(id);
     *     }
     * 
     * @param trip the trip object that is being updated
     * @param id The id of the trip to update
     * @return The trip object is being returned.
     */
    @Override
    public Trip update(Trip trip, String id) {
        if (tripsRepository.existsById(id)) {
            return tripsRepository.save(trip);
        } else {
            System.out.println("Unregistered trip");
            return null;
        }
    }

    /**
     * > This function adds a user to the list of passengers of a trip
     *
     * @param idTrip the id of the trip that the user wants to reserve
     * @param idUser the id of the user who wants to reserve a seat
     * @return A boolean value.
     */
    @Override
    public boolean reservation(String idTrip, String idUser) {
        Trip trip = findById(idTrip);
        ArrayList<String> passengers = trip.getPassengers();
        boolean added = passengers.add(idUser);
        if (added) {
            trip.setPassengers(passengers);
            trip.setAvailableSeats( new AtomicInteger(trip.getAvailableSeats().decrementAndGet()));
            update(trip, trip.getId());
            return true;
        } else {
            return false;
        }
    }

    /**
     * If the number of available seats is equal to the number of passengers, return false, otherwise
     * return true.
     * 
     * @param idTrip String
     * @return A boolean value.
     */
    @Override
    public boolean fullQuotas( String idTrip ) {
        Trip trip = findById(idTrip);
        if(trip.getAvailableSeats().get() == trip.getPassengers().size()){ 
            trip.setActive(false);
            return false;
        }
        return true;
    }

    /**
     * > The function removes a user from the list of passengers of a trip
     *
     * @param idTrip the id of the trip
     * @param idUser the id of the user who wants to reserve a seat
     * @return A boolean value.
     */
    @Override
    public boolean removeReservation(String idTrip, String idUser) {
        Trip trip = findById(idTrip);
        ArrayList<String> passengers = trip.getPassengers();
        boolean removed = passengers.remove(idUser);
        if (removed) {
            trip.setPassengers(passengers);
            update(trip, trip.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Trip> getTripsByIdUserTraveler(String idUser) {
        List<Trip> tripList = getAll();
        List<Trip> result = new ArrayList<>();
        if (!tripList.isEmpty()) {
            for (Trip t : tripList) {
                if (t.getPassengers().contains(idUser)) {
                    result.add(t);
                }
            }
        }
        return result;
    }
}
