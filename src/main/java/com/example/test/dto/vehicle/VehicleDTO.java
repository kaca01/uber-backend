package com.example.test.dto.vehicle;

import com.example.test.domain.ride.Location;
import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;

import java.util.ArrayList;

public class VehicleDTO {

    private Long id;
    private Long driverId;
    private String vehicleType;
    private String model;
    private String licenseNumber;
    private Location currentLocation;
    private int passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;


    public VehicleDTO() {
    }

    public VehicleDTO(Driver driver, Vehicle vehicle) {
        this(vehicle.getId(), driver.getId(), vehicle.getType().getName().toString(), vehicle.getModel(), vehicle.getLicenseNumber(),
                vehicle.getCurrentLocation(), vehicle.getPassengerSeats(), vehicle.isBabyTransport(), vehicle.isPetTransport());
    }

    // response
    public VehicleDTO(Long id, Long driverId, String vehicleType, String model, String licenseNumber,
                      Location currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
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
                      Location currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
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

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(int passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public boolean getBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean getPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }
}
