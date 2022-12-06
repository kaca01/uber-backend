package com.example.test.domain.user;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.vehicle.Vehicle;

import java.util.ArrayList;

public class Driver extends User {
    // TODO add documents to upload
    private int drivingLicense;
    private ArrayList<WorkingHour> workingHours;
    private Vehicle vehicle;

    public Driver() {

    }

    public Driver(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                  String address, String password, boolean blocked, boolean active,
                  int drivingLicense, ArrayList<WorkingHour> workingHours, Vehicle vehicle) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, blocked, active);
        this.drivingLicense = drivingLicense;
        this.workingHours = workingHours;
        this.vehicle = vehicle;
    }

    public int getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(int drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public ArrayList<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(ArrayList<WorkingHour> workingHours) {
        this.workingHours = workingHours;
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
                "drivingLicense=" + drivingLicense +
                ", workingHours=" + workingHours +
                ", vehicle=" + vehicle +
                '}';
    }
}
