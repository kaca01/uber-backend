package com.example.test.domain.ride;

import com.example.test.domain.business.Payment;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.RejectionLetter;
import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.enumeration.RideStatus;

import java.time.LocalTime;
import java.util.ArrayList;

public class Ride {
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    private double price;
    private double estimatedTimeInMin;
    private Vehicle vehicle;
    private Driver driver;
    private ArrayList<Passenger> passengers;

    private ArrayList<Review> reviews;

    private RideStatus rideStatus;
    private RejectionLetter rejectionLetter;
    private Message panic;
    private boolean babiesAllowed;
    private boolean petsAllowed;
    private Payment payment;

    private ArrayList<Message> messages;

    private Route route;

    public Ride() {

    }

    public Ride(int id, LocalTime startTime, LocalTime endTime, double price, double estimatedTimeInMin, Vehicle vehicle,
                Driver driver, ArrayList<Passenger> passengers, ArrayList<Review> reviews, RideStatus rideStatus,
                RejectionLetter rejectionLetter, Message panic, boolean babiesAllowed, boolean petsAllowed,
                Payment payment, ArrayList<Message> messages, Route route) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.estimatedTimeInMin = estimatedTimeInMin;
        this.vehicle = vehicle;
        this.driver = driver;
        this.passengers = passengers;
        this.reviews = reviews;
        this.rideStatus = rideStatus;
        this.rejectionLetter = rejectionLetter;
        this.panic = panic;
        this.babiesAllowed = babiesAllowed;
        this.petsAllowed = petsAllowed;
        this.payment = payment;
        this.messages = messages;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEstimatedTimeInMin() {
        return estimatedTimeInMin;
    }

    public void setEstimatedTimeInMin(double estimatedTimeInMin) {
        this.estimatedTimeInMin = estimatedTimeInMin;
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

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public RejectionLetter getRejectionLetter() {
        return rejectionLetter;
    }

    public void setRejectionLetter(RejectionLetter rejectionLetter) {
        this.rejectionLetter = rejectionLetter;
    }

    public Message getPanic() {
        return panic;
    }

    public void setPanic(Message panic) {
        this.panic = panic;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", estimatedTimeInMin=" + estimatedTimeInMin +
                ", vehicle=" + vehicle +
                ", driver=" + driver +
                ", passengers=" + passengers +
                ", reviews=" + reviews +
                ", rideStatus=" + rideStatus +
                ", rejectionLetter=" + rejectionLetter +
                ", panic=" + panic +
                ", babiesAllowed=" + babiesAllowed +
                ", petsAllowed=" + petsAllowed +
                ", payment=" + payment +
                ", messages=" + messages +
                ", route=" + route +
                '}';
    }
}
