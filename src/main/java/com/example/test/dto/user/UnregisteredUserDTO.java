package com.example.test.dto.user;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^true$|^false$")
    private String petTransport;
    @NotNull
    @Pattern(regexp = "^true$|^false$")
    private String babyTransport;
    @Min(0)
    private int estimatedTimeInMinutes;
    @Min(0)
    private double estimatedCost;

    //request
    public UnregisteredUserDTO(ArrayList<Route> locations, String vehicleType, String petTransport,
                               String babyTransport) {
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
