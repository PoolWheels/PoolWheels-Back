package com.PoolWheels.dto;

import java.util.Date;

public class TokenDto {

    private String token;
    private Date expirationDate;

    public TokenDto( String token, Date expirationDate )
    {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    /**
     * This function returns the token
     * @return The token is being returned.
     */
    public String getToken() {
        return token;
    }

    /**
     * This function returns the expiration date of the credit card
     * @return The expiration date of the credit card.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }
}