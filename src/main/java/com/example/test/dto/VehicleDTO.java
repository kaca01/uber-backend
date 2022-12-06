package com.example.test.dto;

import com.example.test.domain.ride.Location;

import java.util.ArrayList;

public class VehicleDTO {

    private Long id;
    private Long driverId;
    private String vehicleType;
    private String model;
    private String licenseNumber;
    private ArrayList<Location> currentLocation;
    private int passengerSeats;
    private Boolean babyTransport;
    private Boolean petTransport;


    public VehicleDTO() {
    }

    // response
    public VehicleDTO(Long id, Long driverId, String vehicleType, String model, String licenseNumber,
                      ArrayList<Location> currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        super();
        this.id = id;
        this.driverId = driverId;
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    // request
    public VehicleDTO(String vehicleType, String model, String licenseNumber,
                      ArrayList<Location> currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        super();
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public ArrayList<Location> getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(ArrayList<Location> currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(int passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public Boolean getBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(Boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public Boolean getPetTransport() {
        return petTransport;
    }

    public void setPetTransport(Boolean petTransport) {
        this.petTransport = petTransport;
    }
}
