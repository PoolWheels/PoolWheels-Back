package com.UNIWheels.entities;

import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserTravelerDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Document
public class UserTraveler extends User {

    private String pay_methods;



    public UserTraveler(){

    }

    public UserTraveler(UserTravelerDTO userTravellerDTO) {
        super(userTravellerDTO.getId(), userTravellerDTO.getName(), userTravellerDTO.getLastName(), userTravellerDTO.getEmail(), BCrypt.hashpw(userTravellerDTO.getPassword(), BCrypt.gensalt()), userTravellerDTO.getUniversity(), userTravellerDTO.getPhone(), userTravellerDTO.getRol());
        this.pay_methods = userTravellerDTO.getPay_methods();
    }

    public String getPay_methods() {
        return pay_methods;
    }

    public void setPay_methods(String pay_methods) {
        this.pay_methods = pay_methods;
    }
}
