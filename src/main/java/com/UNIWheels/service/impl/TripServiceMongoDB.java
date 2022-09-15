package com.UNIWheels.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UNIWheels.entities.Trip;
import com.UNIWheels.entities.User;
import com.UNIWheels.repository.TripRepository;
import com.UNIWheels.repository.UserDriverRepository;
import com.UNIWheels.repository.UserTravelerRepository;
import com.UNIWheels.service.TripService;

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
        tripsRepository.save(trip);
        return trip;
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
     */
    @Override
    public void deleteById(String id) {
        tripsRepository.deleteById(id);
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
        create(trip);
        return findById(id);
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
        if(trip.getAvailableSeats() == trip.getPassengers().size()){ 
            trip.setActive(false);
            return false;
        }
        return true;
    }

    
    @Override
    public void removeReservation(String idTrip, String idUser) {
        Trip trip = findById(idTrip);
        User user = 
        ArrayList<User> passengers = trip.getPassengers();
        passengers.remove();
    }
    
    
    
}
