package com.UNIWheels.repository;

// This is importing the MongoRepository and the Trips class.
import org.springframework.data.mongodb.repository.MongoRepository;

import com.UNIWheels.entities.Trips;

// Creating a repository for the Trips class.
public interface TripsRepository extends MongoRepository<Trips, String>{
    
}
