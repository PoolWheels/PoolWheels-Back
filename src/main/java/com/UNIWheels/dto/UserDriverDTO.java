package com.UNIWheels.dto;

public class UserDriverDTO {
    private String name;

    private  String lastName;

    private  String email;

    private  String password;

    private String university;

    private String document;

    private int phone;

    private String photo;



    public UserDriverDTO(String name, String lastName, String email, String password, String university, String document, int phone, String photo, String pay_methods) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.university = university;
        this.document = document;
        this.phone = phone;
        this.photo = photo;
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
