package com.example.test.domain.vehicle;

import com.example.test.enumeration.VehicleModel;

public class Vehicle {
    private int id;
    private int driverId;
    private VehicleModel model;
    private int type;
    private String licensePlate;
    private int numberOfSeats;
    private int locationId;
    private boolean babiesAllowed;
    private boolean petsAllowed;

    private Vehicle() {

    }

    public Vehicle(int id, int driverId, VehicleModel model, int type, String licensePlate, int numberOfSeats,
                   int locationId, boolean babiesAllowed, boolean petsAllowed) {
        this.id = id;
        this.driverId = driverId;
        this.model = model;
        this.type = type;
        this.licensePlate = licensePlate;
        this.numberOfSeats = numberOfSeats;
        this.locationId = locationId;
        this.babiesAllowed = babiesAllowed;
        this.petsAllowed = petsAllowed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
                ", driverId=" + driverId +
                ", model=" + model +
                ", type=" + type +
                ", licensePlate='" + licensePlate + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", locationId=" + locationId +
                ", babiesAllowed=" + babiesAllowed +
                ", petsAllowed=" + petsAllowed +
                '}';
    }
}
