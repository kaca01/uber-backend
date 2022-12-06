package com.example.test.domain.ride;

public class Route {
    private Long id;
    private Location departure;
    private Location destination;

    public Route() {

    }

    public Route(Long id, Location departure, Location destination) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startingPoint=" + departure +
                ", endingPoint=" + destination +
                '}';
    }
}
