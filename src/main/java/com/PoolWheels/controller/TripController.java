package com.PoolWheels.controller;

import com.PoolWheels.dto.TripDto;
import com.PoolWheels.entities.Trip;
import com.PoolWheels.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping( "/api/v1/trip" )
public class TripController {

    @Autowired
    private final TripService tripService;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * It is the User Controller class constructor
     *
     * @param tripService The Trips service
     */
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    /* Trips services */

    /**
     * It gets all the trips from the database and returns them as a list of TripDto objects
     *
     * @return A list of TripDto objects.
     */
    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips () {
        try {
            List<Trip> tripList = tripService.getAll();
            ArrayList<TripDto> data = new ArrayList<TripDto>();
            if (!tripList.isEmpty()) {
                for (Trip t : tripList) {
                    data.add(modelMapper.map(t, TripDto.class));
                }
            }
            return new ResponseEntity<List<TripDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<TripDto>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It returns a trip by id.
     *
     * @param id The id of the trip you want to retrieve.
     * @return A trip object
     */
    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable String id) {
        try {
            Trip tripTemp = tripService.findById(id);
            if (tripTemp != null) {
                return new ResponseEntity<TripDto>(modelMapper.map(tripTemp, TripDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("\nDoesn´t exist a trip with the specified id");
            return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TripDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It creates a new trip.
     *
     * @param newTripDto The object that will be created.
     * @return The new trip created.
     */
    @PostMapping
    public ResponseEntity<TripDto> createNewTrip (@RequestBody TripDto newTripDto) {
        try {
            Trip tripTemp = tripService.create(modelMapper.map(newTripDto, Trip.class));
            if (tripTemp != null) {
                return new ResponseEntity<TripDto>(modelMapper.map(tripTemp, TripDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TripDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It updates a trip with the given id.
     *
     * @param tripDto The object that will be updated.
     * @param id The id of the trip to be updated.
     * @return A ResponseEntity object is being returned.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip (@RequestBody TripDto tripDto, @PathVariable String id) {
        try {
            Trip tripTemp = tripService.update(modelMapper.map(tripDto, Trip.class), id);
            if (tripTemp != null) {
                return new ResponseEntity<TripDto>(modelMapper.map(tripTemp, TripDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TripDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It deletes a trip by id.
     *
     * @param id The id of the trip to be deleted
     * @return A boolean value is being returned.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTrip (@PathVariable String id) {
        try {
            boolean deleted = tripService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It returns a boolean value that indicates whether the trip with the given id has full quotas or not
     *
     * @param id The id of the trip
     * @return A boolean value.
     */
    @GetMapping("/{id}/availableseats")
    public ResponseEntity<Boolean> areFullQuotas (@PathVariable String id) {
        try {
            boolean fullQuotas = tripService.fullQuotas(id);
            if (fullQuotas) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * The function receives the id of the trip and the id of the passenger, and returns a boolean value indicating whether
     * the reservation was successful or not
     *
     * @param idTrip The id of the trip that the passenger wants to reserve.
     * @param idPassenger The passenger's ID.
     * @return A boolean value.
     */
    @PostMapping("/{idTrip}/passengers/{idPassenger}")
    public ResponseEntity<Boolean> doReservartion (@PathVariable String idTrip, @PathVariable String idPassenger) {
        try {
            boolean reserved = tripService.reservation(idTrip, idPassenger);
            if (reserved) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It removes a passenger from a trip.
     *
     * @param idTrip the id of the trip
     * @param idPassenger The id of the passenger to be removed from the trip.
     * @return A boolean value.
     */
    @DeleteMapping("/{idTrip}/passengers/{idPassenger}")
    public ResponseEntity<Boolean> removePassengerReservation (@PathVariable String idTrip, @PathVariable String idPassenger) {
        try {
            boolean removed = tripService.removeReservation(idTrip, idPassenger);
            if (removed) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
