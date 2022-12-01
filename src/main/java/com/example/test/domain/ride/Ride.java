package com.example.test.domain.ride;

import com.example.test.enumeration.RideStatus;

import java.time.LocalTime;

public class Ride {
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    private double price;
    private int driverId;
    private int passengerId;  // TODO : not passengers list?
    // TODO : no list of routes?
    private int estimatedTimeInMin;  // TODO : or double?
    private int reviewId;  // TODO : here is not list of reviews because only one passenger can order ??? (no split fare)
    private RideStatus rideStatus;
    private int rejectionLetterId;
    private boolean panicPressed;
    // TODO : do we really need flag for babies and pets
    private boolean babiesAllowed;
    private boolean petsAllowed;
    // TODO : should here be vehicle instead of vehicle type, flags for babies and pets?
    private int vehicleTypeId;
    private int paymentId;

    public Ride() {

    }

    public Ride(int id, LocalTime startTime, LocalTime endTime, double price, int driverId, int passengerId,
                int estimatedTimeInMin, int reviewId, RideStatus rideStatus, int rejectionLetterId,
                boolean panicPressed, boolean babiesAllowed, boolean petsAllowed, int vehicleTypeId, int paymentId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.estimatedTimeInMin = estimatedTimeInMin;
        this.reviewId = reviewId;
        this.rideStatus = rideStatus;
        this.rejectionLetterId = rejectionLetterId;
        this.panicPressed = panicPressed;
        this.babiesAllowed = babiesAllowed;
        this.petsAllowed = petsAllowed;
        this.vehicleTypeId = vehicleTypeId;
        this.paymentId = paymentId;
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

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getEstimatedTimeInMin() {
        return estimatedTimeInMin;
    }

    public void setEstimatedTimeInMin(int estimatedTimeInMin) {
        this.estimatedTimeInMin = estimatedTimeInMin;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public int getRejectionLetterId() {
        return rejectionLetterId;
    }

    public void setRejectionLetterId(int rejectionLetterId) {
        this.rejectionLetterId = rejectionLetterId;
    }

    public boolean isPanicPressed() {
        return panicPressed;
    }

    public void setPanicPressed(boolean panicPressed) {
        this.panicPressed = panicPressed;
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

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", driverId=" + driverId +
                ", passengerId=" + passengerId +
                ", estimatedTimeInMin=" + estimatedTimeInMin +
                ", reviewId=" + reviewId +
                ", rideStatus=" + rideStatus +
                ", rejectionLetterId=" + rejectionLetterId +
                ", panicPressed=" + panicPressed +
                ", babiesAllowed=" + babiesAllowed +
                ", petsAllowed=" + petsAllowed +
                ", vehicleTypeId=" + vehicleTypeId +
                ", paymentId=" + paymentId +
                '}';
    }
}
