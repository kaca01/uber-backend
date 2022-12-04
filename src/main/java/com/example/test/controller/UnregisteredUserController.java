package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/unregisteredUser")
public class UnregisteredUserController {

    @Autowired
    private IUnregisteredUserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Double>> createGreeting(@RequestBody Collection<Location> locations, @RequestBody Vehicle vehicle)
            throws Exception {
        Collection<Double> estimatedValues = service.getEstimationTimeAndCost(locations, vehicle);

        if(estimatedValues == null) {
            return new ResponseEntity<Collection<Double>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Collection<Double>>(estimatedValues, HttpStatus.CREATED);
    }
}
