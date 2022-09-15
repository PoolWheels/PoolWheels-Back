package com.UNIWheels.dto;

public class UserTravelerDTO {

    private String id;

    private String name;

    private  String lastName;

    private  String email;

    private  String password;

    private String university;

    private int phone;

    private String pay_methods;

    private String rol;


    public UserTravelerDTO(String name, String lastName, String email, String password, String university, int phone, String pay_methods,String rol) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.university = university;
        this.phone = phone;
        this.pay_methods = pay_methods;
        this.rol=rol;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}