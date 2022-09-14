package com.UNIWheels.entities;

import com.UNIWheels.dto.UserTravelerDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserTraveler {

    @Id
    private String id;

    private String name;

    private  String lastName;

    private  String email;

    private  String password;

    private String university;

    private int phone;

    private String pay_methods;

    public UserTraveler(UserTravelerDTO userTravelerDTO){
        this.name = userTravelerDTO.getName();
        this.lastName = userTravelerDTO.getLastName();
        this.email = userTravelerDTO.getEmail();
        this.password = userTravelerDTO.getPassword();
        this.university = userTravelerDTO.getUniversity();
        this.phone = userTravelerDTO.getPhone();
        this.pay_methods = userTravelerDTO.getPay_methods();
    }

    public UserTraveler(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPay_methods() {
        return pay_methods;
    }

    public void setPay_methods(String pay_methods) {
        this.pay_methods = pay_methods;
    }
}
