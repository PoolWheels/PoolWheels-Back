package com.UNIWheels.entities;

import com.UNIWheels.dto.UserDriverDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;


@Document
public class UserDriver extends User {

    private String document;
    private String photoCar;
    private String modeloCar;
    private String plate;

    public UserDriver() {
    }

    public UserDriver(UserDriverDTO userDriverDTO) {
        super(userDriverDTO.getId(), userDriverDTO.getName(), userDriverDTO.getLastName(), userDriverDTO.getEmail(), BCrypt.hashpw(userDriverDTO.getPassword(), BCrypt.gensalt()), userDriverDTO.getUniversity(), userDriverDTO.getPhone(), userDriverDTO.getRol());
        this.document = userDriverDTO.getDocument();
        this.photoCar= userDriverDTO.getPhoto();
        this.modeloCar = userDriverDTO.getModeloCar();
        this.plate = userDriverDTO.getPlate();
    }

    /*public UserDriver(String id, String name, String lastName, String email, String password, String university, int phone, String rol) {
        super(id, name, lastName, email, BCrypt.hashpw(password, BCrypt.gensalt()), university, phone, rol);
    }*/

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhotoCar() {
        return photoCar;
    }

    public void setPhotoCar(String photoCar) {
        this.photoCar = photoCar;
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
