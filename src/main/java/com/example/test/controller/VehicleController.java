package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.vehicle.VehicleDTO;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private IVehicleService service;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // Change location of the vehicle
    @PutMapping(value = "/{id}/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @Valid @RequestBody Location location) {
        Vehicle vehicle = this.service.update(id, location);
        this.simpMessagingTemplate.convertAndSend("/map-updates/update-vehicle-position", vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
}
