package com.UNIWheels.service;

import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserTravelerDTO;
import com.UNIWheels.entities.UserDriver;
import com.UNIWheels.entities.UserTraveler;

public interface UserService {
    UserDriver createUserDriver( UserDriverDTO userDto);

    UserTraveler createUserTraveler(UserTravelerDTO userTravelerDTO);


}
