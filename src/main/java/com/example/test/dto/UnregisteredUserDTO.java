package com.example.test.dto;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class UnregisteredUserDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Route> locations;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String vehicleType;
    private boolean petTransport;
    private boolean babyTransport;
    private double estimatedTimeInMinutes;
    private double estimatedCost;

    public UnregisteredUserDTO()
    {

    }

    //request
    public UnregisteredUserDTO(ArrayList<Route> locations, String vehicleType, boolean petTransport,
                               boolean babyTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.petTransport = petTransport;
        this.babyTransport = babyTransport;
    }

    //response
    public UnregisteredUserDTO(double estimatedTimeInMinutes, double estimatedCost) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
    }

    public double getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(double estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public ArrayList<Route> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Route> locations) {
        this.locations = locations;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    @Override
    public String toString() {
        return "UnregisteredUserDTO{" +
                "locations=" + locations +
                ", vehicleType='" + vehicleType + '\'' +
                ", petTransport=" + petTransport +
                ", babyTransport=" + babyTransport +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", estimatedCost=" + estimatedCost +
                '}';
    }
}
