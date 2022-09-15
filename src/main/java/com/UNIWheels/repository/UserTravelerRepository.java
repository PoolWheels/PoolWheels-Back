package com.UNIWheels.repository;

import com.UNIWheels.entities.User;
import com.UNIWheels.entities.UserTraveler;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserTravelerRepository extends MongoRepository<UserTraveler,String> {
    Optional<User> findByEmail(String email);

}
