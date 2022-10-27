package com.PoolWheels.service;

import com.PoolWheels.entities.User;
import com.PoolWheels.entities.UserDriver;
import com.PoolWheels.entities.UserTraveler;

import java.util.List;

//This interface has the methods that will be implemented in UserServiceImpl

public interface UserService {

    UserDriver createUserDriver( UserDriver userDriver);

    UserDriver updateDriver(UserDriver user,String userId);

    boolean deleteDriver(String id);

    UserDriver findByIdDriver(String id);

    List<UserDriver> getAllDriver();

    UserTraveler createUserTraveler(UserTraveler userTraveler);

    UserTraveler updateTraveler(UserTraveler userTraveler,String id);

    boolean deleteTraveler(String id);

    UserTraveler findByIdTraveler(String id);

    List<UserTraveler>getAllTraveler();

    User findByEmail(String email);
}
