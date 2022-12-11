package com.example.test.service.interfaces;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.UserActivation;

import java.util.Collection;
import java.util.List;

public interface IPassengerService {

    public List<Passenger> getAll(Integer page, Integer size);

    Passenger insert(Passenger passenger);

    Passenger update(Passenger passenger, Long passengerId);

    List<Ride> getRidesByPassenger(Long passengerId);

    Passenger findUserById(Long id);

    Boolean activatePassenger(Long activationId);
}
