package com.PoolWheels.repository;

import com.PoolWheels.entities.User;
import com.PoolWheels.entities.UserTraveler;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Creating a repository for the UserTraveler class.
public interface UserTravelerRepository extends MongoRepository<UserTraveler,String> {
    Optional<User> findByEmail(String email);

}
