package com.example.test.domain.ride;

import java.time.LocalDate;

//todo u dokumentu za pitanja su pisali da su rute skroz izbacene, da postoji samo pocetna i krajnja lokacija
//todo u specifikaciji (colicevoj) postoji vise lokacija i definisane su kao lista lokacija u voznji (isto ne postoje rute?)
public class Route {
    private int id;
    private Location startingPoint;
    private Location endingPoint;
    private double distance;
    private double estimatedTime;
    private double price;

    public Route() {

    }

    public Route(int id, Location startingPoint, Location endingPoint, double distance, double estimatedTime, double price) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
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

    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Location getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Location endingPoint) {
        this.endingPoint = endingPoint;
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
                ", startingPoint=" + startingPoint +
                ", endingPoint=" + endingPoint +
                ", distance=" + distance +
                ", estimatedTime=" + estimatedTime +
                ", price=" + price +
                '}';
    }
}
