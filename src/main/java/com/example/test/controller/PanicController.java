package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/panic")
public class PanicController {

    @Autowired
    IPanicService service;

    //Overview of all panic notifications
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<PanicDTO>> getPanicMessages()
    {
        List<PanicDTO> messages = service.getAll();

        AllDTO<PanicDTO> allPanics = new AllDTO<>(messages.size(), messages);
        return new ResponseEntity<>(allPanics, HttpStatus.OK);
    }
}
