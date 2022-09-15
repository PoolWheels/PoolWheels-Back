package com.UNIWheels.repository;

// This is importing the MongoRepository and the Trips class.
import org.springframework.data.mongodb.repository.MongoRepository;

import com.UNIWheels.entities.Trip;

// Creating a repository for the Trips class.
public interface TripRepository extends MongoRepository<Trip, String>{
    
}
