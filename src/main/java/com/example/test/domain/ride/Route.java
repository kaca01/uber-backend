package com.example.test.domain.ride;

import javax.persistence.*;

@Entity
public class Route {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Location departure;
    @ManyToOne
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
