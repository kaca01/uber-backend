package com.example.test.service.interfaces;

import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.ride.RideDTO;

import java.text.ParseException;

public interface IRideService {

    RideDTO insert(RideDTO vehicleType) throws ParseException;

    RideDTO findDriversActiveRide(Long id);

    RideDTO findPassengersActiveRide(Long id);

    RideDTO findOne(Long id);

    RideDTO cancelExistingRide(Long id);

    PanicDTO setPanic(String reason, Long id);

    RideDTO acceptRide(Long id);

    RideDTO endRide(Long id);

    RideDTO cancelRide(String reason, Long id);
}
