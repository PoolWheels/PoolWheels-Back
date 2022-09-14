package com.UNIWheels.repository;

import com.UNIWheels.entities.UserDriver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDriverRepository extends MongoRepository<UserDriver,String> {

}
