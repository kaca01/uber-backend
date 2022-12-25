package com.example.test.service;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.UserActivation;
import com.example.test.repository.IRideRepository;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.repository.user.IUserActivationRepository;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassengerService implements IPassengerService {

    @Autowired
    private IPassengerRepository passengerRepository;
    @Autowired
    private IRideRepository rideRepository;
    @Autowired
    private IUserActivationRepository userActivationRepository;

    //get only those whose active status is true
    @Override
    public List<Passenger> getAll(Integer page, Integer size)
    {
        return passengerRepository.findAllByActiveIsTrue();
    }

    @Override
    public Passenger insert(Passenger passenger)
    {
        passenger.setActive(false);
        passenger.setBlocked(false);
        Passenger p = passengerRepository.save(passenger);
        userActivationRepository.save(new UserActivation(p, new Date(), 180));
        return p;
    }

    @Override
    public Passenger update(Passenger passenger, Long passengerId)
    {
        Passenger p = findUserById(passengerId);
        p.setName(passenger.getName());
        p.setSurname(passenger.getSurname());
        if (passenger.getProfilePicture() != null) p.setProfilePicture(passenger.getProfilePicture());
        if (passenger.getTelephoneNumber() != null) p.setTelephoneNumber(passenger.getTelephoneNumber());
        p.setEmail(passenger.getEmail());
        if (passenger.getAddress() != null) p.setAddress(passenger.getAddress());
        p.setPassword(passenger.getPassword());
        return passengerRepository.save(p);
    }

    @Transactional
    @Override
    public List<Ride> getRidesByPassenger(Long passengerId)
    {
        return rideRepository.findByPassengers_id(passengerId);
    }

    @Override
    public Passenger findUserById(Long id)
    {
        return passengerRepository.findById(id).orElseGet(null);
    }

    @Override
    public Boolean activatePassenger(Long activationId) {
        Passenger p = findUserById(activationId);
        UserActivation activation = userActivationRepository.findByUser_id(activationId);
        if (new Date().before(new Date(activation.getDate().getTime() + activation.getLife()*1000L))) {
            p.setActive(true);
            passengerRepository.save(p);
            userActivationRepository.delete(activation); //todo discuss with the team whether to delete this or not
            return true;
        } else {
            userActivationRepository.delete(activation);
            passengerRepository.delete(p);
            return false;
        }
    }
}
