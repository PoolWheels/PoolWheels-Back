package com.UNIWheels.entities;

import com.UNIWheels.entities.enums.enumPayMethods;

public class PayMethods {

    private String id;
    private enumPayMethods Type;
    private String owner;
    private String number;
    private String bank;
    private String expirationDate;

    public PayMethods(){
        this.id = String.valueOf((int)(Math.random()*9));
    }

    public PayMethods(enumPayMethods Type, String owner, String number, String bank, String ExpirationDate){
        this();
        this.Type = Type;
        this.owner = owner;
        this.number= number;
        this.bank = bank;
        this.expirationDate = ExpirationDate;
    }

    public PayMethods(String id,enumPayMethods type, String owner, String number, String bank, String ExpirationDate){
        this(type, owner, number, bank, ExpirationDate);
        this.id = id;
    }

    public enumPayMethods getType() {
        return Type;
    }

    public String getBank() {
        return bank;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(enumPayMethods type) {
        Type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
