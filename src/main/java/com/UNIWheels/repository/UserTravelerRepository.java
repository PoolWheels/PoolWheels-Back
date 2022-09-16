package com.UNIWheels.repository;

import com.UNIWheels.entities.User;
import com.UNIWheels.entities.UserTraveler;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Creating a repository for the UserTraveler class.
public interface UserTravelerRepository extends MongoRepository<UserTraveler,String> {
    Optional<User> findByEmail(String email);

}
