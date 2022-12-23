package com.example.test.domain.ride;

import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.ride.RideDTO;
import com.example.test.enumeration.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ride {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "startTime", nullable = false)
    private Date startTime;
    @Column(name = "endTime")
    private Date endTime;
    @Column(name = "totalCost")
    private double totalCost;
    @Column(name = "estimatedTimeInMinutes")
    private double estimatedTimeInMinutes;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Driver driver;
    @ManyToMany
    private Set<Passenger> passengers = new HashSet<>();
    @OneToMany
    private Set<Review> reviews = new HashSet<>();
    @Column(name = "status", nullable = false)
    private RideStatus status;
    @OneToOne
    private Rejection rejection;
    @Column(name = "babyTransport", nullable = false)
    //private Message panic;          bidirectional relation!!!
    private boolean babyTransport;
    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;
    @ManyToOne
    private Route route;

    public Ride(RideDTO rideDTO)
    {
        this.setRoute(rideDTO.getRoute());
        this.setBabyTransport(rideDTO.isBabyTransport());
        this.setPetTransport(rideDTO.isPetTransport());
    }

    public Ride(Long id) {
        this.id = id;
    }
}
