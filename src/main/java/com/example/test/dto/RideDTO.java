package com.example.test.dto;

import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;

import java.util.ArrayList;
import java.util.Date;


public class RideDTO {
    // TODO add necessary dtos
    private Long id;
    private String startTime;
    private String endTime;
    private double totalCost;
    private ArrayList<Route> locations;
    private ArrayList<UserDTO> passengers;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private double estimatedTimeInMinutes;
    private String status;
    private UserDTO driver;
    private RejectionDTO rejection;


    public RideDTO() {

    }

    public RideDTO(Ride ride) {
        this.id = ride.getId();
        this.startTime = ride.getStartTime().toString();
        this.endTime = ride.getEndTime().toString();
        this.totalCost = ride.getTotalCost();
        this.locations = ride.getLocations();
        this.passengers = convertPassengersToUsersDTO(ride);
        this.vehicleType = ride.getVehicle().getType().getName().toString();
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.status = ride.getStatus().toString();
        this.driver = new UserDTO(ride.getDriver());
        this.rejection = new RejectionDTO(ride.getRejection());
    }

    // request
    public RideDTO(ArrayList<Route> locations, ArrayList<UserDTO> passengers, String vehicleType,
                   boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }


    // response
    public RideDTO(Long id, String startTime, String endTime, double totalCost, ArrayList<Route> locations,
                   ArrayList<UserDTO> passengers, String vehicleType, boolean babyTransport,
                   boolean petTransport, double estimatedTimeInMinutes, String status, UserDTO driver,
                   RejectionDTO rejection) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.status = status;
        this.driver = driver;
        this.rejection = rejection;
    }

    public ArrayList<UserDTO> convertPassengersToUsersDTO(Ride ride) {
        ArrayList<UserDTO> users = new ArrayList<UserDTO>();
        for (Passenger passenger: ride.getPassengers()) {
            users.add(new UserDTO(passenger.getId(), passenger.getEmail()));
        }
        return users;
    }

    public ArrayList<Route> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Route> locations) {
        this.locations = locations;
    }

    public ArrayList<UserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<UserDTO> passengers) {
        this.passengers = passengers;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(double estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getDriver() {
        return driver;
    }

    public void setDriver(UserDTO driver) {
        this.driver = driver;
    }

    public RejectionDTO getRejection() {
        return rejection;
    }

    public void setRejection(RejectionDTO rejection) {
        this.rejection = rejection;
    }
}