package com.UNIWheels.service;

import java.util.List;

import com.UNIWheels.entities.Trip;

public interface TripService {

    Trip create( Trip trip );

    Trip findById( String id );

    List<Trip> getAll();

    boolean deleteById( String id );

    Trip update( Trip trip, String id );

    //void reservation( Users user);

    boolean fullQuotas( String idTrip );

    void removeReservation( String idTrip, String idUser );
    
}
