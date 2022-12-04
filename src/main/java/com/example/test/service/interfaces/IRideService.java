package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;

import java.util.Collection;

public interface IRideService {

    Ride insert(Ride ride);

    Ride findDriversActiveRide(int id);

    Ride findPassengersActiveRide(int id);

    Ride findRideById(int id);

    boolean cancelExistingRide(int id);

    Message setPanic(String reason, int id);

    Ride acceptRide(int id);

    Ride endRide(int id);

    Ride cancelRide(String reason, int id);
}
