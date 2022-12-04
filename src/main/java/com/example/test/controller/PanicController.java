package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPanicService;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/panic")
public class PanicController {

    @Autowired
    IPanicService service;

    @GetMapping
    public Collection<Message> getPanicMessages() {return service.getAll();}
}
