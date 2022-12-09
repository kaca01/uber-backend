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

    // Change location of the vehicle
    @PutMapping(value = "/{id}/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> update(@PathVariable int id, @RequestBody Location location)
            throws Exception {

        Boolean updatedLocation = service.update((long) id, location);

        if (!updatedLocation) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo 400
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
