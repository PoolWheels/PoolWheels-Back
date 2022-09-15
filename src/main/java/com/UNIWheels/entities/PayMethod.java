package com.UNIWheels.entities;

// This is importing the necessary packages for the class.
import com.UNIWheels.dto.PayMethodDto;
import com.UNIWheels.enums.enumPayMethod;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * The class PayMethods is a document that has an id, a type, an owner, a number, a bank and an expiration date
 */
@Document
public class PayMethod {

    @Id
    private String id;
    private enumPayMethod Type;
    private String owner;
    @Indexed(unique = true)
    private String number;
    private String bank;
    private String expirationDate;

    // Creating a random id for the paymethods.
    public PayMethod(){
        this.id = String.valueOf((int)(Math.random()*9));
    }

    // A constructor that takes a PayMethodsDto object and creates a PayMethods object.
    public PayMethod(PayMethodDto dto) {
        this.id = dto.getId();
        this.Type = dto.getType();
        this.owner = dto.getOwner();
        this.number = dto.getNumber();
        this.bank = dto.getBank();
        this.expirationDate = dto.getExpirationDate();
    }

    // This is a constructor that takes in the parameters and sets them to the variables.
    public PayMethod(enumPayMethod Type, String owner, String number, String bank, String ExpirationDate){
        this();
        this.Type = Type;
        this.owner = owner;
        this.number= number;
        this.bank = bank;
        this.expirationDate = ExpirationDate;
    }

    // This is a constructor that takes in the parameters and sets them to the variables.
    public PayMethod(String id, enumPayMethod type, String owner, String number, String bank, String ExpirationDate){
        this(type, owner, number, bank, ExpirationDate);
        this.id = id;
    }

    /**
     * This function returns the type of payment method
     *
     * @return The type of payment method.
     */
    public enumPayMethod getType() {
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
     * This function returns the number of the card.
     *
     * @return The number of the card.
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
    public void setType(enumPayMethod type) {
        Type = type;
    }

    /**
     * This function sets the number of the phone to the number passed in.
     *
     * @param number The number of the card
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
     * @param owner The owner of the repository.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This function sets the expiration date of thecard
     *
     * @param expirationDate The date the card expires.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
