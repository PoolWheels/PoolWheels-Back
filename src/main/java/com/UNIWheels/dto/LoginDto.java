package com.UNIWheels.dto;

public class LoginDto {

    String email;
    String password;

    public LoginDto( String email, String password ) {
        this.email = email;
        this.password = password;
    }

    /**
     * This function returns the email of the user
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This function returns the password.
     *
     * @return The password
     */
    public String getPassword() {
        return password;
    }
}