package com.example.test.domain.ride;

import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "startTime")
    @PastOrPresent
    private Date startTime;
    @Column(name = "endTime")
    @PastOrPresent
    private Date endTime;
    @Column(name = "totalCost")
    private double totalCost;
    @Column(name = "estimatedTimeInMinutes")
    private double estimatedTimeInMinutes;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Vehicle vehicle;
    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();
    @Column(name = "status", nullable = false)
    private RideStatus status;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Rejection rejection;
    @Column(name = "babyTransport", nullable = false)
    private boolean babyTransport;
    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Route> locations = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id")
    private Set<Review> reviews = new HashSet<>();
    @Column(nullable = false)
    private Date scheduledTime;

    public Ride(RideDTO rideDTO) throws ParseException {
        System.out.println("USAO U KONSTRUKTOR");
        this.setLocations(rideDTO.getLocations());
        this.setBabyTransport(rideDTO.isBabyTransport());
        this.setPetTransport(rideDTO.isPetTransport());

        if(rideDTO.getScheduledTime() != null) 
            this.scheduledTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                            .parse(rideDTO.getScheduledTime());
    }

    public Ride(Long id) {
        this.id = id;
    }
}
