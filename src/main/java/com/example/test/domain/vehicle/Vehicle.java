package com.example.test.domain.vehicle;

import com.example.test.domain.ride.Location;
import com.example.test.enumeration.VehicleModel;

public class Vehicle {
    private int id;
    private VehicleType type;
    private VehicleModel model;
    private String licensePlate;
    private int numberOfSeats;
    private Location currentLocation;
    private boolean babiesAllowed;
    private boolean petsAllowed;

    private Vehicle() {

    }

    public Vehicle(int id, VehicleType type, VehicleModel model, String licensePlate, int numberOfSeats, Location currentLocation, boolean babiesAllowed, boolean petsAllowed) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.licensePlate = licensePlate;
        this.numberOfSeats = numberOfSeats;
        this.currentLocation = currentLocation;
        this.babiesAllowed = babiesAllowed;
        this.petsAllowed = petsAllowed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isBabiesAllowed() {
        return babiesAllowed;
    }

    public void setBabiesAllowed(boolean babiesAllowed) {
        this.babiesAllowed = babiesAllowed;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", currentLocation=" + currentLocation +
                ", babiesAllowed=" + babiesAllowed +
                ", petsAllowed=" + petsAllowed +
                '}';
    }
}
