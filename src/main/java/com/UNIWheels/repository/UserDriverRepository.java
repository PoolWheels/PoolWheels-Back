package com.UNIWheels.repository;

import com.UNIWheels.entities.User;
import com.UNIWheels.entities.UserDriver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Creating a repository for the UserDriver class.
public interface UserDriverRepository extends MongoRepository<UserDriver,String> {
    Optional<User> findByEmail(String email);
}
