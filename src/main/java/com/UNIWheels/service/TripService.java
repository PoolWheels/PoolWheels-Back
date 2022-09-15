package com.UNIWheels.service;

import java.util.List;

import com.UNIWheels.entities.Trip;
import com.UNIWheels.entities.User;

public interface TripService {

    Trip create( Trip trip );

    Trip findById( String id );

    List<Trip> getAll();

    boolean deleteById( String id );

    Trip update( Trip trip, String id );

    boolean reservation(String trip, String user);

    boolean fullQuotas( String idTrip );

    boolean removeReservation(String idTrip, String idUser );
    
}
