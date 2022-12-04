package com.example.test.controller;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    IPassengerService service;

    //izgenerisati id unutra
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Passenger> insert(@RequestBody Passenger passenger) throws Exception
    {
        Passenger newPassenger = service.insert(passenger);
        if (newPassenger == null)
            return new ResponseEntity<Passenger>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Passenger>(newPassenger, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Passenger>> getPassengers()
    {
        Collection<Passenger> passengers = service.getAll();
        return new ResponseEntity<Collection<Passenger>>(passengers, HttpStatus.OK);
    }

    //treba da ako nije isteklo vrijeme, da user-u prebacim aktivnost na active
    @PostMapping(value = "/{activationId}")
    public ResponseEntity<Boolean> activatePassenger(@PathVariable Long activationId) throws Exception
    {
        Boolean flag = service.activatePassenger(activationId);
        if (!flag) {
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        //TODO add error 400 (bad request=invalid data)
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Passenger> findUserById(@PathVariable Long id)
    {
        Passenger passenger = service.findUserById(id);

        if (passenger == null) {
            return new ResponseEntity<Passenger>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Passenger> update(@RequestBody Passenger passenger, @PathVariable Long id) throws Exception
    {
        Passenger newPassenger = service.update(passenger, id);

        if (newPassenger == null) {
            return new ResponseEntity<Passenger>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<Passenger>(newPassenger, HttpStatus.OK);
    }

    //todo paginatorske voznje ??
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ride>> getRidesByPassenger(@PathVariable Long id)
    {
        Collection<Ride> rides = service.getRidesByPassenger(id);
        if (rides == null) {
            return new ResponseEntity<Collection<Ride>>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<Collection<Ride>>(rides, HttpStatus.OK);
    }
}
