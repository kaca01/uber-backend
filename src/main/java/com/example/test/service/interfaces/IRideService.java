package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;

import java.util.Collection;

public interface IRideService {

    Ride insert(Ride ride);

    Ride findDriversActiveRide(Long id);

    Ride findPassengersActiveRide(Long id);

    Ride findRideById(Long id);

    Boolean cancelExistingRide(Long id);

    Message setPanic(String reason, Long id);

    Ride acceptRide(Long id);

    Ride endRide(Long id);

    Ride cancelRide(String reason, Long id);
}
