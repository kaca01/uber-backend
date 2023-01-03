package com.example.test.dto.vehicle;

import com.example.test.domain.ride.Location;
import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Data
public class VehicleDTO {

    private Long id;
    private Long driverId;
    private String vehicleType;
    private String model;
    private String licenseNumber;
    private Location currentLocation;
    private int passengerSeats;
    private boolean babyTransport;
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
