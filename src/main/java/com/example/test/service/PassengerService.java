package com.example.test.service;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;

import java.util.Collection;

public class PassengerService implements IPassengerService {

    @Override
    public Collection<Passenger> getAll() {
        return null;
    }

    @Override
    public Passenger insert(Passenger passenger) {
        return null;
    }

    @Override
    public Passenger update(Passenger passenger, int passengerId) {
        return null;
    }

    @Override
    public Collection<Ride> getRidesByPassenger(int passengerId) {
        return null;
    }

    @Override
    public Passenger findUserById(int id) {
        return null;
    }

    @Override
    public boolean activatePassenger(int activationId) {
        return false;
    }
}
