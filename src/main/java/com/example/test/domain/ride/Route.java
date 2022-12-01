package com.example.test.domain.ride;

import java.time.LocalDate;

public class Route {
    private int id;
    private LocalDate startTime;
    private LocalDate endTime;
    private int startingPointId;  // this is from location class
    private int endingPointId;
    private double distance;
    private double estimatedTime;
    private double price;

    public Route() {

    }

    public Route(int id, LocalDate startTime, LocalDate endTime, int startingPointId, int endingPointId,
                 double distance, double estimatedTime, double price) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startingPointId = startingPointId;
        this.endingPointId = endingPointId;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public int getStartingPointId() {
        return startingPointId;
    }

    public void setStartingPointId(int startingPointId) {
        this.startingPointId = startingPointId;
    }

    public int getEndingPointId() {
        return endingPointId;
    }

    public void setEndingPointId(int endingPointId) {
        this.endingPointId = endingPointId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startingPointId=" + startingPointId +
                ", endingPointId=" + endingPointId +
                ", distance=" + distance +
                ", estimatedTime=" + estimatedTime +
                ", price=" + price +
                '}';
    }
}
