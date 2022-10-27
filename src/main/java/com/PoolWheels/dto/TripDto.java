package com.PoolWheels.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TripDto {
    
    private String id;
    private String driver;
    private String addrInit;
    private String addrFin;
    private AtomicInteger availableSeats;
    private HashMap<String, Integer> stops;
    private ArrayList<String> passengers;
    private String initTime;
    private String finTime = null;
    private boolean active = false;

    // It's a constructor.
    public TripDto(){
    }

    // It's a constructor.
    public TripDto(String driver, String addrInit, String addrFin, AtomicInteger availableSeats,
            HashMap<String, Integer> stops, ArrayList<String> passengers, String initTime, String finTime,
            boolean active) {
        this();
        this.driver = driver;
        this.addrInit = addrInit;
        this.addrFin = addrFin;
        this.availableSeats = availableSeats;
        this.stops = stops;
        this.passengers = passengers;
        this.initTime = initTime;
        this.finTime = finTime;
        this.active = active;
    }

    // It's a constructor.
    public TripDto(String id, String driver, String addrInit, String addrFin, AtomicInteger availableSeats,
            HashMap<String, Integer> stops, ArrayList<String> passengers, String initTime, String finTime,
            boolean active) {
        this(driver, addrInit, addrFin, availableSeats, stops, passengers, initTime, finTime, active);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAddrInit() {
        return addrInit;
    }

    public void setAddrInit(String addrInit) {
        this.addrInit = addrInit;
    }

    public String getAddrFin() {
        return addrFin;
    }

    public void setAddrFin(String addrFin) {
        this.addrFin = addrFin;
    }

    public AtomicInteger getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(AtomicInteger availableSeats) {
        this.availableSeats = availableSeats;
    }

    public HashMap<String, Integer> getStops() {
        return stops;
    }

    public void setStops(HashMap<String, Integer> stops) {
        this.stops = stops;
    }

    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<String> passengers) {
        this.passengers = passengers;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public String getFinTime() {
        return finTime;
    }

    public void setFinTime(String finTime) {
        this.finTime = finTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
