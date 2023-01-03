package com.example.test.domain.vehicle;

import com.example.test.domain.ride.Location;
import com.example.test.dto.vehicle.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleType type;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "licenseNumber", nullable = false)
    private String licenseNumber;
    @Column(name = "passengerSeats", nullable = false)
    private int passengerSeats;
    @ManyToOne(fetch = FetchType.EAGER)
    private Location currentLocation;
    @Column(name = "babyTransport", nullable = false)
    private boolean babyTransport;
    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;


    public Vehicle() {
        this.type = new VehicleType();
        this.currentLocation = new Location();
    }

    public Vehicle(VehicleDTO vehicleDTO) {
        this.id = vehicleDTO.getId();
        this.licenseNumber = vehicleDTO.getLicenseNumber();
        this.passengerSeats = vehicleDTO.getPassengerSeats();
        this.currentLocation = vehicleDTO.getCurrentLocation();
        this.babyTransport = vehicleDTO.isBabyTransport();
        this.petTransport = vehicleDTO.isPetTransport();
        this.model = vehicleDTO.getModel();
    }

    public VehicleType getType()
    {
        if (this.type == null) this.type = new VehicleType();
        return type;
    }

    public Location getCurrentLocation()
    {
        if (this.currentLocation == null) this.currentLocation = new Location();
        return currentLocation;
    }

}
