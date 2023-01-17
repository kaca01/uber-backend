package com.example.test.dto.user;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@NoArgsConstructor
@Data
public class UnregisteredUserDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @NotNull
    private ArrayList<Route> locations;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @NotNull
    private String vehicleType;
    @NotNull
    private boolean petTransport;
    @NotNull
    private boolean babyTransport;
    @Min(0)
    private int estimatedTimeInMinutes;
    @Min(0)
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
