package com.PoolWheels.service.impl;

import com.PoolWheels.entities.User;
import com.PoolWheels.entities.UserDriver;
import com.PoolWheels.entities.UserTraveler;
import com.PoolWheels.repository.UserDriverRepository;
import com.PoolWheels.repository.UserTravelerRepository;
import com.PoolWheels.service.UserService;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/*
*This class is a service that implements the UserService interface
*/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserDriverRepository userDriverRepository;

    @Autowired
    private UserTravelerRepository userTravelerRepository;

    public UserServiceImpl(UserDriverRepository userDriverRepository, UserTravelerRepository userTravelerRepository) {
        this.userDriverRepository = userDriverRepository;
        this.userTravelerRepository = userTravelerRepository;
    }

    @Override
    public UserDriver createUserDriver(UserDriver userDriver) {
        try {
            userDriver.setPassword(BCrypt.hashpw(userDriver.getPassword(), BCrypt.gensalt()));
            userDriverRepository.insert(userDriver);
            Optional<UserDriver> userTemp = userDriverRepository.findById(userDriver.getId());
            return userTemp.orElse(null);
        } catch (DuplicateKeyException e) {
            System.out.println("The specified id is already registered");
            return null;
        }
    }

    @Override
    public UserDriver updateDriver(UserDriver userDriver,String userId){
        if (userDriverRepository.existsById(userId)) {
            userDriver.setPassword(BCrypt.hashpw(userDriver.getPassword(), BCrypt.gensalt()));
            return userDriverRepository.save(userDriver);
        } else {
            System.out.println("Unregistered user");
            return null;
        }
    }

    @Override
    public boolean deleteDriver(String id){
        try {
            userDriverRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    @Override
    public UserDriver findByIdDriver(String id){
        return userDriverRepository.findById(id).get();
    }
    @Override
    public List<UserDriver> getAllDriver(){
        return userDriverRepository.findAll();
    }


    @Override
    public UserTraveler createUserTraveler(UserTraveler userTraveler){
        try {
            userTraveler.setPassword(BCrypt.hashpw(userTraveler.getPassword(), BCrypt.gensalt()));
            userTravelerRepository.insert(userTraveler);
            Optional<UserTraveler> userT = userTravelerRepository.findById(userTraveler.getId());
            return userT.orElse(null);
        } catch (DuplicateKeyException e) {
            System.out.println("The specified id is already registered");
            return null;
        }
    }
    @Override
    public UserTraveler updateTraveler(UserTraveler userTraveler,String userId){
        if (userTravelerRepository.existsById(userId)) {
            userTraveler.setPassword(BCrypt.hashpw(userTraveler.getPassword(), BCrypt.gensalt()));
            return userTravelerRepository.save(userTraveler);
        } else {
            System.out.println("Unregistered user");
            return null;
        }
    }

    @Override
    public boolean deleteTraveler(String id){
        try {
            userTravelerRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    @Override
    public UserTraveler findByIdTraveler(String id){
        return userTravelerRepository.findById(id).get();
    }
    @Override
    public List<UserTraveler> getAllTraveler(){
        return userTravelerRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        if (userDriverRepository.findByEmail(email).isPresent()){
            return userDriverRepository.findByEmail(email).get();

        } else if(userTravelerRepository.findByEmail(email).isPresent()){
            return userTravelerRepository.findByEmail(email).get();

        } else {
            return null;
        }

    }
}
