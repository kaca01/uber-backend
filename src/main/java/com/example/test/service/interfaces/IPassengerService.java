package com.example.test.service.interfaces;

import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;

import java.util.List;

public interface IPassengerService {

    public List<UserDTO> getAll(Integer page, Integer size);

    UserDTO insert(UserDTO passenger);

    UserDTO update(UserDTO passenger, Long passengerId);

    List<RideDTO> getRidesByPassenger(Long passengerId);

    UserDTO findOne(Long id);

    Boolean activatePassenger(Long activationId);
}
