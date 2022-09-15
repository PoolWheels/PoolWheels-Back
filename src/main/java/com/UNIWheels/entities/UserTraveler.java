package com.UNIWheels.entities;

import com.UNIWheels.dto.UserTravelerDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Document
public class UserTraveler extends User {

    private String pay_methods;


    public UserTraveler(UserTravelerDTO userTravelerDTO) {

        this(userTravelerDTO.getId(), userTravelerDTO.getName(), userTravelerDTO.getLastName(), userTravelerDTO.getEmail(), BCrypt.hashpw(userTravelerDTO.getPassword(), BCrypt.gensalt()), userTravelerDTO.getUniversity(), userTravelerDTO.getPhone(), userTravelerDTO.getRol());
        setPay_methods(pay_methods);



    }
    public UserTraveler(String id, String name, String lastName, String email, String password, String university, int phone, String rol) {
        super(id, name, lastName, email, password, university, phone, rol);
    }

    public String getPay_methods() {
        return pay_methods;
    }

    public void setPay_methods(String pay_methods) {
        this.pay_methods = pay_methods;
    }
}
