package com.UNIWheels.entities;

import com.UNIWheels.dto.UserDriverDTO;
import com.UNIWheels.dto.UserDriverDTO;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class UserDriver {
    @Id
    private String id;

    private String name;

    private  String lastName;

    private  String email;

    private  String password;

    private String university;

    private String document;

    private int phone;

    private String photo;



    public UserDriver(){}

    public UserDriver(UserDriverDTO userDTO){
        this.name = userDTO.getName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.university = userDTO.getUniversity();
        this.document = userDTO.getDocument();
        this.phone = userDTO.getPhone();
        this.photo = userDTO.getPhoto();

    }

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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
