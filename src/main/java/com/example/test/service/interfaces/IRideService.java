package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.dto.RideDTO;

public interface IRideService {

    Ride insert(Ride ride, RideDTO vehicleType);

    Ride findDriversActiveRide(Long id);

    Ride findPassengersActiveRide(Long id);

    Ride findRideById(Long id);

    Ride cancelExistingRide(Long id);

    Message setPanic(String reason, Long id);

    Ride acceptRide(Long id);

    Ride endRide(Long id);

    Ride cancelRide(String reason, Long id);
}
