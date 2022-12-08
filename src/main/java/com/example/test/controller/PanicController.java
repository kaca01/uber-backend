package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPanicService;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/panic")
public class PanicController {

    @Autowired
    IPanicService service;
    
    //Overview of all panic notifications
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Message>> getPanicMessages()
    {
        Collection<Message> messages = service.getAll();
        return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
    }
}
