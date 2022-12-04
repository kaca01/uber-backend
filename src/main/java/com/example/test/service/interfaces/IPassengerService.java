package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.UserActivation;

import java.util.Collection;

public interface IPassengerService {
    public Collection<Passenger> getAll();

    Passenger insert(Passenger passenger);

    Passenger update(Passenger passenger, int passengerId);

    Collection<Ride> getRidesByPassenger(int passengerId);

    Passenger findUserById(int id);

    boolean activatePassenger(int activationId);
}
