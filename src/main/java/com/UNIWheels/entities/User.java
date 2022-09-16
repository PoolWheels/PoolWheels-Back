package com.UNIWheels.entities;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCrypt;
/**
 * The class Abstract User is a document that has an id, a name, an last name, a email, a password,a university,a phone,and a rol.
 */
public abstract class User {

    @Id
    private String id;

    private String name;

    private  String lastName;

    private  String email;

    private  String password;

    private String university;

    private int phone;

    private String rol;

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


    public User(){
        this.id = String.valueOf((int)(Math.random()*5));
    }

    public User(String name,String lastName, String email, String password, String university, int phone, String rol){
        this();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.university=university;
        this.phone = phone;
        this.rol = rol;
    }

    public User(String id, String name,String lastName, String email, String password, String university, int phone, String rol){
        this(name,lastName, email, BCrypt.hashpw(password, BCrypt.gensalt()), university, phone, rol);
        this.id = id;
    }

}
