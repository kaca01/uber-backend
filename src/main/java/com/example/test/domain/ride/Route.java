package com.example.test.domain.ride;

public class Route {
    private int id;
    private Location startingPoint;
    private Location endingPoint;
    private double distance;

    public Route() {

    }

    public Route(int id, Location startingPoint, Location endingPoint, double distance) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.distance = distance;
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


    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startingPoint=" + startingPoint +
                ", endingPoint=" + endingPoint +
                ", distance=" + distance +
                '}';
    }
}
