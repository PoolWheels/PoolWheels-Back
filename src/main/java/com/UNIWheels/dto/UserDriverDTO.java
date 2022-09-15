package com.UNIWheels.dto;

public class UserDriverDTO {

    private String id;

    private String name;

    private  String lastName;

    private  String email;

    private  String password;

    private String university;

    private String document;

    private int phone;

    private String photo;

    private String modeloCar;

    private String plate;

    private String rol;


    public UserDriverDTO(){
        this.id = String.valueOf((int)(Math.random()*5));
    }

    public UserDriverDTO(String name,String lastName, String email, String password, String university, int phone, String rol, String photo, String modelCar, String plate, String document){
        this();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.university=university;
        this.phone = phone;
        this.rol = rol;
        this.document = document;
        this.photo= photo;
        this.modeloCar = modelCar;
        this.plate = plate;
    }

    public UserDriverDTO(String id, String name,String lastName, String email, String password, String university, int phone, String rol, String photo, String modelCar, String plate, String document){
        this(name,lastName, email,password, university, phone,rol, photo , modelCar, plate, document);
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModeloCar() {
        return modeloCar;
    }

    public void setModeloCar(String modeloCar) {
        this.modeloCar = modeloCar;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
