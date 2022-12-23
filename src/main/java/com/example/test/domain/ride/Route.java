package com.example.test.domain.ride;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Route {
    private Long id;
    private Location departure;
    private Location destination;

    public Route(Long id, Location departure, Location destination) {
        this.id = id;
        this.departure = departure;
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
