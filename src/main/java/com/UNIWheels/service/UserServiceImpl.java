package com.UNIWheels.service;

import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserTravelerDTO;
import com.UNIWheels.entities.UserDriver;
import com.UNIWheels.entities.UserTraveler;
import com.UNIWheels.repository.UserDriverRepository;
import com.UNIWheels.repository.UserTravelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserDriverRepository userDriverRepository;

    @Autowired
    private UserTravelerRepository userTravelerRepository;



    @Override
    public UserDriver createUserDriver(UserDriverDTO userDto) {
        return userDriverRepository.save(new UserDriver(userDto));
    }

    @Override
    public UserTraveler createUserTraveler(UserTravelerDTO userTravelerDTO) {
        return userTravelerRepository.save( new UserTraveler(userTravelerDTO));
    }
}
