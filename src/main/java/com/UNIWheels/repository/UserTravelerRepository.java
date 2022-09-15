package com.UNIWheels.repository;

import com.UNIWheels.entities.UserTraveler;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTravelerRepository extends MongoRepository<UserTraveler,String> {
}
