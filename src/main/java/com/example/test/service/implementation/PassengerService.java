package com.example.test.service.implementation;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.UserActivation;
import com.example.test.dto.ErrorDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.repository.user.IUserActivationRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private IUserRepository userRepository;
    @Autowired
    private IRideRepository rideRepository;
    @Autowired
    private IUserActivationRepository userActivationRepository;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    //get only those whose active status is true
    @Override
    public List<UserDTO> getAll(Integer page, Integer size)
    {
        List<Passenger> passengers = passengerRepository.findAllByActiveIsTrue();

        // convert passengers to DTOs
        List<UserDTO> passengersDTO = new ArrayList<>();
        for (Passenger p : passengers) {
            passengersDTO.add(new UserDTO(p));
        }

        return passengersDTO;
    }

    @Override
    public UserDTO insert(UserDTO passengerDTO)
    {
        if (this.userRepository.existsByEmail(passengerDTO.getEmail())) {
            throw new BadRequestException("User with that email already exists!");
        }
        Passenger passenger = new Passenger(passengerDTO);
        passenger.setPassword(passengerDTO.getPassword());
        passenger.setActive(false);
        passenger.setBlocked(false);
        passenger = passengerRepository.save(passenger);
        userActivationRepository.save(new UserActivation(passenger, new Date(), 180));
        return new UserDTO(passenger);
    }

    @Override
    public UserDTO update(UserDTO passengerDTO, Long passengerId)
    {
        Passenger passenger = new Passenger(passengerDTO);
        Passenger p = findUserById(passengerId);
        p.setName(passenger.getName());
        p.setSurname(passenger.getSurname());
        if (passenger.getProfilePicture() != null) p.setProfilePicture(passenger.getProfilePicture());
        if (passenger.getTelephoneNumber() != null) p.setTelephoneNumber(passenger.getTelephoneNumber());
        p.setEmail(passenger.getEmail());
        p.setAddress(passenger.getAddress());
        if (passenger.getPassword() != null) p.setPassword(passenger.getPassword());
        p = passengerRepository.save(p);
        return new UserDTO(p);
    }

    @Transactional
    @Override
    public List<RideDTO> getRidesByPassenger(Long passengerId)
    {
        Passenger p = findUserById(passengerId);
        List<Ride> rides = rideRepository.findByPassengers_id(passengerId);

        // convert rides to DTOs
        List<RideDTO> ridesDTO = new ArrayList<>();
        for (Ride r : rides) {
            ridesDTO.add(new RideDTO(r));
        }

        return ridesDTO;
    }

    @Override
    public UserDTO findOne(Long id)
    {
        Passenger p = findUserById(id);
        return new UserDTO(p);
    }

    private Passenger findUserById(Long id)
    {
        return passengerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Passenger does not exist!"));
    }

    @Override
    public ErrorDTO
    activatePassenger(Long activationId) {
        UserActivation activation = userActivationRepository.findByUser_id(activationId).orElseThrow(
                () -> new NotFoundException("Activation with entered id does not exist!"));
        Passenger p = findUserById(activationId);
        if (new Date().before(new Date(activation.getDate().getTime() + activation.getLife()*1000L))) {
            p.setActive(true);
            passengerRepository.save(p);
            userActivationRepository.delete(activation);
            return new ErrorDTO("Successful account activation!");
        } else {
            userActivationRepository.delete(activation);
            passengerRepository.delete(p);
            throw new BadRequestException("Activation expired. Register again!");
        }
    }
}
