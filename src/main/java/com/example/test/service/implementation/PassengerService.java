package com.example.test.service.implementation;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.ResetPassword;
import com.example.test.domain.user.User;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
    @Autowired
    private JavaMailSender mailSender;

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
    public UserDTO insert(UserDTO passengerDTO) throws MessagingException, UnsupportedEncodingException {
        if (this.userRepository.existsByEmail(passengerDTO.getEmail())) {
            throw new BadRequestException("User with that email already exists!");
        }
        Passenger passenger = new Passenger(passengerDTO);
        passenger.setPassword(passwordEncoder.encode(passengerDTO.getPassword()));
        passenger.setActive(false);
        passenger.setBlocked(false);
        passenger = passengerRepository.save(passenger);
        UserActivation activation = userActivationRepository.save(new UserActivation(passenger, new Date(), 180));
        sendActivationEmail(activation);
        return new UserDTO(passenger);
    }

    public void sendActivationEmail(UserActivation activation) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(activation.getUser().getEmail()).orElseThrow(()
                -> new NotFoundException("User does not exist!"));
        String toAddress = "hristinacina@gmail.com";
        String fromAddress = "anastasijas557@gmail.com";
        String senderName = "Uber Support";
        String subject = "Activate Your Uber Account";
        String content = "Hello [[name]], thank you for joining us!<br>"
                + "To activate your account please follow this link: "
                + "<a href='http://localhost:4200/activation/[[id]]'>activate</a><br>"
                + "The Uber team.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName() + " " + user.getSurname());
        content = content.replace("[[id]]", activation.getId().toString());
        helper.setText(content, true);

        mailSender.send(message);
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
    public ErrorDTO activatePassenger(Long activationId) {
        UserActivation activation = userActivationRepository.findById(activationId).orElseThrow(
                () -> new NotFoundException("Activation with entered id does not exist!"));
        Passenger p = findUserById(activation.getUser().getId());
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

    @Override
    public UserDTO getByEmail(String email) {
        Passenger passenger = passengerRepository.findByEmail(email).orElseThrow(()
                -> new NotFoundException("Passenger does not exist!"));
        UserDTO userDTO = new UserDTO(passenger.getId(), email);
        return userDTO;
    }
}
