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

@NoArgsConstructor
@Data
public class RideDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private double totalCost;
    private Route route;
    private ArrayList<UserDTO> passengers;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private double estimatedTimeInMinutes;
    private String status;
    private UserDTO driver;
    private RejectionDTO rejection;


    public RideDTO(Ride ride) {
        this.id = ride.getId();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.startTime = format.format(ride.getStartTime());
        if(ride.getEndTime() != null) this.endTime = format.format(ride.getEndTime());
        this.totalCost = ride.getTotalCost();
        this.route = ride.getRoute();
        this.passengers = convertPassengersToUsersDTO(ride);
        this.vehicleType = ride.getVehicle().getType().getName().toString();
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.status = ride.getStatus().toString();
        if(ride.getDriver() != null) this.driver = new UserDTO(ride.getDriver());
        if (ride.getRejection() != null) this.rejection = new RejectionDTO(ride.getRejection());
    }

    // request
    public RideDTO(Route route, ArrayList<UserDTO> passengers, String vehicleType,
                   boolean babyTransport, boolean petTransport) {
        this.route = route;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }


    // response
    public RideDTO(Long id, String startTime, String endTime, double totalCost, Route route,
                   ArrayList<UserDTO> passengers, String vehicleType, boolean babyTransport,
                   boolean petTransport, double estimatedTimeInMinutes, String status, UserDTO driver,
                   RejectionDTO rejection) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.route = route;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.status = status;
        this.driver = driver;
        this.rejection = rejection;
    }

    public ArrayList<UserDTO> convertPassengersToUsersDTO(Ride ride) {
        ArrayList<UserDTO> users = new ArrayList<UserDTO>();
        for (Passenger passenger: ride.getPassengers()) {
            users.add(new UserDTO(passenger.getId(), passenger.getEmail()));
        }
        return users;
    }
}