package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.service.interfaces.IRideService;
import org.springframework.stereotype.Service;

@Service
public class RideService implements IRideService {

    @Override
    public Ride insert(Ride ride) {
        return null;
    }

    @Override
    public Ride findDriversActiveRide(Long id) {
        return null;
    }

    @Override
    public Ride findPassengersActiveRide(Long id) {
        return null;
    }

    @Override
    public Ride findRideById(Long id) {
        return null;
    }

    @Override
    public boolean cancelExistingRide(Long id) {
        return false;
    }

    @Override
    public Message setPanic(String reason, Long id) {
        return null;
    }

    @Override
    public Ride acceptRide(Long id) {
        return null;
    }

    @Override
    public Ride endRide(Long id) {
        return null;
    }

    @Override
    public Ride cancelRide(String reason, Long id) {
        return null;
    }
}
