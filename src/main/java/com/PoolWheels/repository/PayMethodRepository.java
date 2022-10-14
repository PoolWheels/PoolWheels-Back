package com.PoolWheels.repository;

// This is importing the PayMethods class and the MongoRepository class.
import com.PoolWheels.entities.PayMethod;
import org.springframework.data.mongodb.repository.MongoRepository;

// Creating a repository for the PayMethods class.
public interface PayMethodRepository extends MongoRepository<PayMethod, String>{
}
