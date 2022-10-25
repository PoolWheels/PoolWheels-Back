package com.PoolWheels.service;

import java.util.List;

import com.PoolWheels.entities.Trip;

public interface TripService {

    Trip create( Trip trip );

    Trip findById( String id );

    List<Trip> getAll();

    boolean deleteById( String id );

    Trip update( Trip trip, String id );

    boolean reservation(String trip, String user);

    boolean fullQuotas( String idTrip );

    boolean removeReservation(String idTrip, String idUser );

    List<Trip> getTripsByIdUserTraveler(String idUser);
}
