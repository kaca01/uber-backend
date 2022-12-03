package com.example.test.domain.user;

import com.example.test.domain.business.WorkTime;
import com.example.test.domain.vehicle.Vehicle;

import java.util.ArrayList;

public class Driver extends User {
    // TODO add documents to upload
    private boolean active;
    private int vehicleRegistration;
    private int drivingLicense;
    private WorkTime workTime;
    private Vehicle vehicle;

    public Driver() {

    }

    public Driver(int id, String name, String phone, String email, String address, String password, boolean blocked,
                  boolean active, int vehicleRegistration, int drivingLicense, WorkTime workTime, Vehicle vehicle) {
        super(id, name, phone, email, address, password, blocked);
        this.active = active;
        this.vehicleRegistration = vehicleRegistration;
        this.drivingLicense = drivingLicense;
        this.workTime = workTime;
        this.vehicle = vehicle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(int vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public int getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(int drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "active=" + active +
                ", vehicleRegistration=" + vehicleRegistration +
                ", drivingLicense=" + drivingLicense +
                ", workTime=" + workTime +
                ", vehicle=" + vehicle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;

        Driver driver = (Driver) o;

        if (active != driver.active) return false;
        if (vehicleRegistration != driver.vehicleRegistration) return false;
        if (drivingLicense != driver.drivingLicense) return false;
        if (!workTime.equals(driver.workTime)) return false;
        return vehicle.equals(driver.vehicle);
    }

    @Override
    public int hashCode() {
        int result = (active ? 1 : 0);
        result = 31 * result + vehicleRegistration;
        result = 31 * result + drivingLicense;
        result = 31 * result + workTime.hashCode();
        result = 31 * result + vehicle.hashCode();
        return result;
    }
}
