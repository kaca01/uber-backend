package com.example.test.dto.ride;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.communication.RejectionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class RideDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private double totalCost;
    private Set<Route> locations;
    private Set<UserDTO> passengers;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private double estimatedTimeInMinutes;
    private String status;
    private UserDTO driver;
    private RejectionDTO rejection;
    private String scheduledTime;


    public RideDTO(Ride ride) {
        this.id = ride.getId();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if(ride.getStartTime() != null) this.startTime = format.format(ride.getStartTime());
        if(ride.getEndTime() != null) this.endTime = format.format(ride.getEndTime());
        this.totalCost = ride.getTotalCost();
        this.locations = ride.getLocations();
        this.passengers = convertPassengersToUsersDTO(ride);
        if (ride.getVehicle() != null) this.vehicleType = ride.getVehicle().getType().getName().toString();
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.status = ride.getStatus().toString();
        this.scheduledTime = format.format(ride.getScheduledTime());
        if(ride.getDriver() != null) this.driver = new UserDTO(ride.getDriver());
        if (ride.getRejection() != null) this.rejection = new RejectionDTO(ride.getRejection());
    }

    // request
    public RideDTO(Set<Route> locations, Set<UserDTO> passengers, String vehicleType,
                   boolean babyTransport, boolean petTransport, String scheduledTime) {
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.scheduledTime = scheduledTime;
    }

    // response
    public RideDTO(Long id, String startTime, String endTime, double totalCost, Set<Route> locations,
                   Set<UserDTO> passengers, String vehicleType, boolean babyTransport,
                   boolean petTransport, double estimatedTimeInMinutes, String status, UserDTO driver,
                   RejectionDTO rejection, String scheduledTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.status = status;
        this.driver = driver;
        this.rejection = rejection;
        this.scheduledTime = scheduledTime;
    }

    private Set<UserDTO> convertPassengersToUsersDTO(Ride ride) {
        Set<UserDTO> users = new HashSet<>();
        for (Passenger passenger: ride.getPassengers()) {
            users.add(new UserDTO(passenger.getId(), passenger.getEmail()));
        }
        return users;
    }
}