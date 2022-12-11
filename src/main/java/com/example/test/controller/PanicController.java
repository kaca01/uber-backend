package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.AllPanicsDTO;
import com.example.test.dto.AllUsersDTO;
import com.example.test.dto.PanicDTO;
import com.example.test.dto.UserDTO;
import com.example.test.service.interfaces.IPanicService;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/panic")
public class PanicController {

    @Autowired
    IPanicService service;
    
    //Overview of all panic notifications
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllPanicsDTO> getPanicMessages()
    {

        List<Message> messages = service.getAll();

        // convert panics to DTOs
        ArrayList<PanicDTO> panicsDTO = new ArrayList<>();
        for (Message m : messages) {
            panicsDTO.add(new PanicDTO(m));
        }
        return new ResponseEntity<AllPanicsDTO>(new AllPanicsDTO(panicsDTO.size(), panicsDTO), HttpStatus.OK);
    }
}
