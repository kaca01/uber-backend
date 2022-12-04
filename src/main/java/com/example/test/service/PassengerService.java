package com.example.test.service;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
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
    public Passenger update(Passenger passenger, Long passengerId) {
        return null;
    }

    @Override
    public Collection<Ride> getRidesByPassenger(Long passengerId) {
        return null;
    }

    @Override
    public Passenger findUserById(Long id) {
        return null;
    }

    @Override
    public Boolean activatePassenger(Long activationId) {
        return false;
    }
}
