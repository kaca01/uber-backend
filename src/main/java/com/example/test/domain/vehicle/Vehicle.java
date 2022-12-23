package com.example.test.domain.vehicle;

import com.example.test.domain.ride.Location;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Vehicle {
    private Long id;
    private VehicleType type;
    private String model;
    private String licenseNumber;
    private int passengerSeats;
    private Location currentLocation;
    private boolean babyTransport;
    private boolean petTransport;

    public Vehicle(Long id, VehicleType type, String model, String licenseNumber, int passengerSeats,
                   Location currentLocation, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.currentLocation = currentLocation;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getType()
    {
        if (this.type == null) this.type = new VehicleType();
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(int passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public Location getCurrentLocation()
    {
        if (this.currentLocation == null) this.currentLocation = new Location();
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", passengerSeats=" + passengerSeats +
                ", currentLocation=" + currentLocation +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                '}';
    }

    // TODO move this to service
    VehicleType findVehicleTypeByName(String name) {
        // TODO not implemented yet
        return null;
    }
}
