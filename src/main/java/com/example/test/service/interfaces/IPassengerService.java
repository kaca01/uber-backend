package com.example.test.service.interfaces;

import com.example.test.dto.ErrorDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IPassengerService {

    public List<UserDTO> getAll(Integer page, Integer size);

    UserDTO insert(UserDTO passenger) throws MessagingException, UnsupportedEncodingException;

    UserDTO update(UserDTO passenger, Long passengerId);

    List<RideDTO> getRidesByPassenger(Long passengerId);

    UserDTO findOne(Long id);

    ErrorDTO activatePassenger(Long activationId);

    List<UserDTO> getByEmails(String[] emails);
}
