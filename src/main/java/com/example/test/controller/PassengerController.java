package com.example.test.controller;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    IPassengerService service;

    //izgenerisati id unutra
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Passenger insert(@RequestBody Passenger passenger)
    {
        return service.insert(passenger);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Passenger> getPassengers()
    {
        return service.getAll();
    }

    //treba da ako nije isteklo vrijeme, da user-u prebacim aktivnost na active
    //todo da li activation id ili passenger id?? i da li treba producer ovdje?
    @PostMapping(value = "/{activationId}")
    public boolean activatePassenger(@PathVariable Long activationId)
    {
        return service.activatePassenger(activationId);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Passenger findUserById(@PathVariable Long id)
    {
        return service.findUserById(id);
    }

    //gotovo
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Passenger update(@RequestBody Passenger passenger, @PathVariable Long id)
    {
        return service.update(passenger, id);
    }

    //todo paginatorske voznje ??
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Ride> getRidesByPassenger(@PathVariable Long id)
    {
        return service.getRidesByPassenger(id);
    }
}
