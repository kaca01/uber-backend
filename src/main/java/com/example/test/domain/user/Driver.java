package com.example.test.domain.user;

import java.util.ArrayList;

public class Driver extends User {
    // TODO add documents to upload
    private boolean active;
    private ArrayList<Integer> rides;
    private int vehicleInt;

    public Driver() {

    }

    public Driver(int id, String name, String phone, String email, String address, String password, boolean blocked,
                  boolean active, ArrayList<Integer> rides, int vehicleInt) {
        super(id, name, phone, email, address, password, blocked);
        this.active = active;
        this.rides = rides;
        this.vehicleInt = vehicleInt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Integer> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Integer> rides) {
        this.rides = rides;
    }

    public int getVehicleInt() {
        return vehicleInt;
    }

    public void setVehicleInt(int vehicleInt) {
        this.vehicleInt = vehicleInt;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "active=" + active +
                ", rides=" + rides +
                ", vehicleInt=" + vehicleInt +
                '}';
    }
}
