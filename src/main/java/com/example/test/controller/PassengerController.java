package com.example.test.controller;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    IPassengerService service;

    //izgenerisati id unutra
    @PostMapping
    public Passenger insert(@RequestBody Passenger passenger) {
        return service.insert(passenger);
    }

    @GetMapping
    public Collection<Passenger> getPassengers() {return service.getAll();}

    //treba da ako nije isteklo vrijeme, da user-u prebacim aktivnost na active
    //todo da li activation id ili passenger id??
    @PostMapping("/{activationId}")
    public boolean activatePassenger(@PathVariable Long activationId) {
        return service.activatePassenger(activationId);
    }

    @GetMapping("/{id}")
    public Passenger findUserById(@PathVariable Long id) {
        return service.findUserById(id);
    }

    //gotovo
    @PutMapping("/{id}")
    public Passenger update(@RequestBody Passenger passenger, @PathVariable Long id) {
        return service.update(passenger, id);
    }

    //todo paginatorske voznje ??
    @GetMapping("/{id}/ride")
    public Collection<Ride> getRidesByPassenger(@PathVariable Long id) {return service.getRidesByPassenger(id);}
}
