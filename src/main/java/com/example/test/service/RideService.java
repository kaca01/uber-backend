package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.RideStatus;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.repository.communication.IRejectionRepository;
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
    @Autowired
    private IRejectionRepository rejectionRepository;

    @Transactional
    @Override
    public RideDTO insert(RideDTO rideDTO) {

        Ride ride = new Ride(rideDTO);
        Set<Passenger> passengers = new HashSet<>();

        for (UserDTO u : rideDTO.getPassengers()) {
            Passenger p = passengerRepository.findById(u.getId()).orElse(null);
            passengers.add(p);
        }
        ride.setPassengers(passengers);
        ride.setStatus(RideStatus.PENDING);
        findAvailableDriver(ride, rideDTO.getVehicleType());

        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    private void findAvailableDriver(Ride ride, String vehicleType) {
        //metoda nalazi slobodnog aktivnog vozaca koji je najblizi polazistu i cije vozilo odgovara zeljenom tipu i ostalim zahtjevima (baby i pet i br putnika)
        //ako nema slobodnog, trazi zauzetog bla bla
        //setuje vozaca, vozilo, pocetno vrijeme, kraj vremena, cijena, procijenjeno vrijeme
        ride.setStartTime(new Date());
    }

    @Transactional
    @Override
    public RideDTO findDriversActiveRide(Long id) {
        Ride ride = rideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, id);
        if(ride == null) return null;
        return new RideDTO(ride);
    }

    @Transactional
    @Override
    public RideDTO findPassengersActiveRide(Long id) {
        Ride ride = rideRepository.findByStatusAndPassengers_id(RideStatus.ACTIVE, id);
        if(ride == null) return null;
        return new RideDTO(ride);
    }

    @Override
    public RideDTO findOne(Long id) {
        Ride r = findRideById(id);
        if (r == null) return null;
        return new RideDTO(r);
    }

    private Ride findRideById(Long id){ return rideRepository.findById(id).orElse(null);}

    //The passenger should have the possibility to cancel an existing ride before the driver has arrived at the destination
    @Override
    public RideDTO cancelExistingRide(Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        if ( !ride.getLocations().stream().findFirst().get().getDeparture().equals(ride.getVehicle().getCurrentLocation()) &&
                (ride.getStatus()==RideStatus.ACCEPTED || ride.getStatus()==RideStatus.PENDING)){
            ride.setStatus(RideStatus.REJECTED);
            ride = rideRepository.save(ride);
            return new RideDTO(ride);
        }
        return null;
    }

    //the user will be used from the token
    @Override
    public PanicDTO setPanic(String reason, Long id)
    {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.REJECTED);
        ride = rideRepository.save(ride);
        //todo sender will be received from the token (wont be ride.getDriver()) and should Rejection be here as well?No?
        Message panic = new Message(ride.getDriver(), null, reason, new Date(), MessageType.PANIC, ride);
        panic = messageRepository.save(panic);
        return new PanicDTO(panic);
    }

    @Override
    public RideDTO acceptRide(Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.ACCEPTED);
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    @Override
    public RideDTO endRide(Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.FINISHED);
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    //perspective of driver
    @Override
    public RideDTO cancelRide(String reason, Long id) {
        Ride ride = findRideById(id);
        if(ride == null) return null;
        ride.setStatus(RideStatus.REJECTED);
        Rejection rejection = new Rejection(reason, ride.getDriver(), new Date());
        ride.setRejection(rejection);
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }
}
