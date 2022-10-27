package com.PoolWheels.dto;

import com.PoolWheels.enums.EnumPayMethod;

/**
 * A DTO class that represents a PayMethod.
 */
public class PayMethodDto {

    private String id;
    private EnumPayMethod Type;
    private String owner;
    private String number;
    private String bank;
    private String expirationDate;

    // Creating a random number between 0 and 9 and setting it to the id.
    public PayMethodDto(){

    }

    // A constructor that takes in the parameters and sets them to the class variables.
    public PayMethodDto(EnumPayMethod Type, String owner, String number, String bank, String ExpirationDate){
        this();
        this.Type = Type;
        this.owner = owner;
        this.number= number;
        this.bank = bank;
        this.expirationDate = ExpirationDate;
    }

    // This is a constructor that takes in the parameters and sets them to the class variables.
    public PayMethodDto(String id, EnumPayMethod type, String owner, String number, String bank, String ExpirationDate){
        this(type, owner, number, bank, ExpirationDate);
        this.id = id;
    }

    /**
     * This function returns the type of the payment method
     *
     * @return The type of payment method.
     */
    public EnumPayMethod getType() {
        return Type;
    }

    /**
     * This function returns the bank name
     *
     * @return The bank name.
     */
    public String getBank() {
        return bank;
    }

    /**
     * This function returns the id of the current object
     *
     * @return The id of the object.
     */
    public String getId() {
        return id;
    }

    /**
     * This function returns the number of the phone.
     *
     * @return The number of the phone.
     */
    public String getNumber() {
        return number;
    }

    /**
     * This function returns the owner of the card.
     *
     * @return The owner of the card.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This function returns the expiration date of the card
     *
     * @return The expiration date of the card.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * This function sets the id of the object to the id passed in as a parameter
     *
     * @param id The id of the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This function sets the type of payment method
     *
     * @param type The type of payment method.
     */
    public void setType(EnumPayMethod type) {
        Type = type;
    }

    /**
     * This function sets the number of the phone to the number passed in.
     *
     * @param number The phone number to send the message to.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * This function sets the bank variable to the value of the bank parameter.
     *
     * @param bank The name of the bank.
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * This function sets the owner of the card.
     *
     * @param owner The owner of the card.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This function sets the expiration date of the card
     *
     * @param expirationDate The date the expires.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
