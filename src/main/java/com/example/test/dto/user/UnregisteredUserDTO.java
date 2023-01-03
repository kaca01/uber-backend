package com.example.test.dto.user;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Data
public class UnregisteredUserDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Route> locations;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String vehicleType;
    private boolean petTransport;
    private boolean babyTransport;
    private int estimatedTimeInMinutes;
    private double estimatedCost;

    //request
    public UnregisteredUserDTO(ArrayList<Route> locations, String vehicleType, boolean petTransport,
                               boolean babyTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.petTransport = petTransport;
        this.babyTransport = babyTransport;
    }

    //response
    public UnregisteredUserDTO(int estimatedTimeInMinutes, double estimatedCost) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
    }
}
