package com.PoolWheels.repository;

import com.PoolWheels.entities.User;
import com.PoolWheels.entities.UserDriver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Creating a repository for the UserDriver class.
public interface UserDriverRepository extends MongoRepository<UserDriver,String> {
    Optional<User> findByEmail(String email);
}
