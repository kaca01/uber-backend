package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PanicService implements IPanicService{

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public List<Message> getAll()
    {
        return messageRepository.findByType(MessageType.PANIC);
    }

}
