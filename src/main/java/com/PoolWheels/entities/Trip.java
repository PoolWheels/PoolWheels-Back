package com.PoolWheels.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.PoolWheels.dto.TripDto;

@Document
/**
 * It's a class that represents a trip.
 */
public class Trip {

    @Id
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

    // Generating a random number between 0 and 9 and assigning it to the id.
    public Trip(){
        this.id = String.valueOf((int)(Math.random()*9));
    }

    // It's a constructor.
    public Trip(String driver, String addrInit, String addrFin, AtomicInteger availableSeats, HashMap<String, Integer> stops,
            ArrayList<String> passengers, String initTime) {
        this();
        this.driver = driver;
        this.addrInit = addrInit;
        this.addrFin = addrFin;
        this.availableSeats = availableSeats;
        this.stops = stops;
        this.passengers = passengers;
        this.initTime = initTime;
    }

    // It's a constructor.
    public Trip(TripDto dto) {
        this.id = dto.getId();
        this.driver = dto.getDriver();
        this.addrInit = dto.getAddrInit();
        this.addrFin = dto.getAddrFin();
        this.availableSeats = dto.getAvailableSeats();
        this.stops = dto.getStops();
        this.passengers = dto.getPassengers();
        this.initTime = dto.getInitTime();
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
