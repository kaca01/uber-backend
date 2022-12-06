package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private IVehicleService service;

    @PutMapping(value = "/{id}/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody Location location)
            throws Exception {
        Boolean updatedLocation = service.update(id, location);
        // TODO : add 400 status
        if (updatedLocation == null) {
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(updatedLocation, HttpStatus.OK);
    }
}
