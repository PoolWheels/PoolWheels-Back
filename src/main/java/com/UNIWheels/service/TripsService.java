package com.UNIWheels.service;

import java.util.List;

import com.UNIWheels.entities.Trips;

public interface TripsService {

    Trips create( Trips trip );

    Trips findById( String id );

    List<Trips> getAll();

    void deleteById( String id );

    Trips update( Trips trip, String id );

    boolean fullQuotas( String idTrip );

    void removeReservation( String idTrip );
    
}
