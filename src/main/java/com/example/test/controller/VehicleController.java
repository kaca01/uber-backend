package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private IVehicleService service;

    // Change location of the vehicle
    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping(value = "/{id}/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> update(@PathVariable int id, @Valid @RequestBody Location location) {
        service.update((long) id, location);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }
}
