package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.RideStatus;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class RideService implements IRideService {

    @Autowired
    private IPassengerRepository passengerRepository;
    @Autowired
    private IRideRepository rideRepository;
    @Autowired
    private IMessageRepository messageRepository;

    @Transactional
    @Override
    public Ride insert(RideDTO rideDTO) {

        Ride ride = new Ride(rideDTO);
        Set<Passenger> passengers = new HashSet<>();

        for (UserDTO u : rideDTO.getPassengers()) {
            Passenger p = passengerRepository.findById(u.getId()).orElseGet(null);
            passengers.add(p);
        }
        ride.setPassengers(passengers);
        ride.setStatus(RideStatus.PENDING);
        findAvailableDriver(ride, rideDTO.getVehicleType());

        return rideRepository.save(ride);
    }

    private void findAvailableDriver(Ride ride, String vehicleType) {
        //metoda nalazi slobodnog aktivnog vozaca koji je najblizi polazistu i cije vozilo odgovara zeljenom tipu i ostalim zahtjevima (baby i pet i br putnika)
        //ako nema slobodnog, trazi zauzetog bla bla
        //setuje vozaca, vozilo, pocetno vrijeme, kraj vremena, cijena, procijenjeno vrijeme
        ride.setStartTime(new Date());
    }

    @Override
    public Ride findDriversActiveRide(Long id) {
        return rideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, id);
    }

    @Override
    public Ride findPassengersActiveRide(Long id) {
        return rideRepository.findByStatusAndPassengers_id(RideStatus.ACTIVE, id);
    }

    @Override
    public Ride findRideById(Long id) {
        return rideRepository.findById(id).orElseGet(null);
    }

    //The passenger should have the possibility to cancel an existing ride before the driver has arrived at the destination
    @Override
    public Ride cancelExistingRide(Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        if ( !ride.getRoute().getDeparture().equals(ride.getVehicle().getCurrentLocation()) &&
                (ride.getStatus()==RideStatus.ACCEPTED || ride.getStatus()==RideStatus.PENDING)){
            ride.setStatus(RideStatus.REJECTED);
            return rideRepository.save(ride);
        }
        return null;
    }

    //the user will be used from the token
    @Override
    public Message setPanic(String reason, Long id)
    {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.REJECTED); //todo is this supposed to be like this?
        //todo sender will be received from the token (wont be ride.getDriver())
        Message panic = new Message(ride.getDriver(), null, reason, new Date(), MessageType.PANIC, ride);
        return messageRepository.save(panic);
    }

    @Override
    public Ride acceptRide(Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.ACCEPTED);
        return rideRepository.save(ride);
    }

    @Override
    public Ride endRide(Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.FINISHED);
        return rideRepository.save(ride);
    }

    //perspective of driver
    @Override
    public Ride cancelRide(String reason, Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.REJECTED);
        ride.setRejection(new Rejection(reason, ride.getDriver(), new Date()));  // da li ce ovo automatski kreirati novi rejection?
        return rideRepository.save(ride);
    }
}
