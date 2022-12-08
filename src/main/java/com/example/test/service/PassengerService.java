package com.example.test.service;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PassengerService implements IPassengerService {

    @Override
    public List<Passenger> getAll(Integer page, Integer size) {
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
    public List<Ride> getRidesByPassenger(Long passengerId) {
        return null;
    }

    @Override
    public Passenger findUserById(Long id) {
        return null;
    }

    //activationId == id of the passenger
    //treba da ako nije isteklo vrijeme, da user-u prebacim aktivnost na active
    @Override
    public Boolean activatePassenger(Long activationId) {
        return false;
    }
}
