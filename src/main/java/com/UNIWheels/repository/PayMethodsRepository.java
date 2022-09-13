package com.UNIWheels.repository;

import com.UNIWheels.entities.PayMethods;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PayMethodsRepository extends MongoRepository<PayMethods, String>{
}
