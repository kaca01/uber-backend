package com.example.test.service.interfaces;

import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.ride.RideDTO;

public interface IRideService {

    RideDTO insert(RideDTO vehicleType);

    RideDTO findDriversActiveRide(Long id);

    RideDTO findPassengersActiveRide(Long id);

    RideDTO findOne(Long id);

    RideDTO cancelExistingRide(Long id);

    PanicDTO setPanic(String reason, Long id);

    RideDTO acceptRide(Long id);

    RideDTO endRide(Long id);

    RideDTO cancelRide(String reason, Long id);

    RideDTO startRide(Long id);
}
