package com.UNIWheels.service.impl;

import com.UNIWheels.entities.User;
import com.UNIWheels.entities.UserDriver;
import com.UNIWheels.entities.UserTraveler;
import com.UNIWheels.repository.UserDriverRepository;
import com.UNIWheels.repository.UserTravelerRepository;
import com.UNIWheels.service.UserService;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
            System.out.println("User found in driver");
            return userDriverRepository.findByEmail(email).get();
        } else if(userTravelerRepository.findByEmail(email).isPresent()){
            System.out.println("User found in traveler");
            return userTravelerRepository.findByEmail(email).get();
        } else
            return null;
    }
}
