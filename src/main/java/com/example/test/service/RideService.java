package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.service.interfaces.IRideService;

public class RideService implements IRideService {

    @Override
    public Ride insert(Ride ride) {
        return null;
    }

    @Override
    public Ride findDriversActiveRide(int id) {
        return null;
    }

    @Override
    public Ride findPassengersActiveRide(int id) {
        return null;
    }

    @Override
    public Ride findRideById(int id) {
        return null;
    }

    @Override
    public boolean cancelExistingRide(int id) {
        return false;
    }

    @Override
    public Message setPanic(String reason, int id) {
        return null;
    }

    @Override
    public Ride acceptRide(int id) {
        return null;
    }

    @Override
    public Ride endRide(int id) {
        return null;
    }

    @Override
    public Ride cancelRide(String reason, int id) {
        return null;
    }
}
