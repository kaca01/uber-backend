package com.example.test.dto.vehicle;

import com.example.test.domain.ride.Location;
import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@NoArgsConstructor
@Data
public class VehicleDTO {

    private Long id;
    private Long driverId;
    @NotEmpty
    @NotNull
    private String vehicleType;
    @NotEmpty
    @NotNull
    @Length(max = 100,  message = "Max 100 characters allowed for vehicle model!")
    private String model;
    @NotEmpty
    @NotNull
    @Length(max = 20, message = "Max 20 characters allowed for license number!")
    private String licenseNumber;
    @NotNull
    private Location currentLocation;
    @NotNull
    @Min(value = 1, message = "There must be at least 1 seat!")
    @Max(value = 20, message = "Max 18 seats allowed!")
    private int passengerSeats;
    @NotNull
    private boolean babyTransport;
    @NotNull
    private boolean petTransport;

    public VehicleDTO(Driver driver, Vehicle vehicle) {
        this(vehicle.getId(), driver.getId(), vehicle.getType().getName().toString(), vehicle.getModel(), vehicle.getLicenseNumber(),
                vehicle.getCurrentLocation(), vehicle.getPassengerSeats(), vehicle.isBabyTransport(), vehicle.isPetTransport());
    }

    // response
    public VehicleDTO(Long id, Long driverId, String vehicleType, String model, String licenseNumber,
                      Location currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        super();
        this.id = id;
        this.driverId = driverId;
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    // request
    public VehicleDTO(String vehicleType, String model, String licenseNumber,
                      Location currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        super();
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

}
