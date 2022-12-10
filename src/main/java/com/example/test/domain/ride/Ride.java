package com.example.test.domain.ride;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.RideDTO;
import com.example.test.dto.UserDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.service.PassengerService;

import java.util.ArrayList;
import java.util.Date;

public class Ride {
    private Long id;
    private Date startTime;
    private Date endTime;
    private double totalCost;
    private double estimatedTimeInMinutes;
    private Vehicle vehicle;
    private Driver driver;
    private ArrayList<Passenger> passengers;
    private ArrayList<Review> reviews;
    private RideStatus status;
    private Rejection rejection;
    private Message panic;
    private boolean babyTransport;
    private boolean petTransport;
    private ArrayList<Route> locations;

    public Ride() {

    }

    public Ride(Long id, Date startTime, Date endTime, double totalCost, double estimatedTimeInMinutes, Vehicle vehicle,
                Driver driver, ArrayList<Passenger> passengers, ArrayList<Review> reviews, RideStatus status,
                Rejection rejection, Message panic, boolean babyTransport, boolean petTransport,
                ArrayList<Route> locations) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicle = vehicle;
        this.driver = driver;
        this.passengers = passengers;
        this.reviews = reviews;
        this.status = status;
        this.rejection = rejection;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.locations = locations;
    }

    public Ride(RideDTO rideDTO)
    {
        this.setLocations(rideDTO.getLocations());
        this.setBabyTransport(rideDTO.isBabyTransport());
        this.setPetTransport(rideDTO.isPetTransport());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public Rejection getRejection() {
        return rejection;
    }

    public void setRejection(Rejection rejection) {
        this.rejection = rejection;
    }

    public Message getPanic() {
        return panic;
    }

    public void setPanic(Message panic) {
        this.panic = panic;
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

    public ArrayList<Route> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Route> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + totalCost +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", vehicle=" + vehicle +
                ", driver=" + driver +
                ", passengers=" + passengers +
                ", reviews=" + reviews +
                ", ride=" + status +
                ", rejection=" + rejection +
                ", panic=" + panic +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", locations=" + locations +
                '}';
    }
}
