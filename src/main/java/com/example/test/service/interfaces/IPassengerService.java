package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.UserActivation;

import java.util.Collection;

public interface IPassengerService {
    public Collection<Passenger> getAll();

    Passenger insert(Passenger passenger);

    Passenger update(Passenger passenger, Long passengerId);

    Collection<Ride> getRidesByPassenger(Long passengerId);

    Passenger findUserById(Long id);

    boolean activatePassenger(Long activationId);
}
