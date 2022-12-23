package com.example.test.domain.ride;

import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.ride.RideDTO;
import com.example.test.enumeration.RideStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
    //private Message panic;          bidirectional relation!!!
    private boolean babyTransport;
    private boolean petTransport;
    private ArrayList<Route> locations;

    public Ride(Long id, Date startTime, Date endTime, double totalCost, double estimatedTimeInMinutes, Vehicle vehicle,
                Driver driver, ArrayList<Passenger> passengers, ArrayList<Review> reviews, RideStatus status,
                Rejection rejection, boolean babyTransport, boolean petTransport,
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
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.locations = locations;
    }

    public Ride(Long id) {
        this.id = id;
   }

    public Ride(RideDTO rideDTO)
    {
        this.setLocations(rideDTO.getLocations());
        this.setBabyTransport(rideDTO.isBabyTransport());
        this.setPetTransport(rideDTO.isPetTransport());
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
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", locations=" + locations +
                '}';
    }
}
