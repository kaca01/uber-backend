package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;

    @PostMapping
    public Ride insert(@RequestBody Ride ride) {
        return service.insert(ride);
    }

    @GetMapping("/active/{driverId}")
    public Ride findDriversActiveRide(@PathVariable Long id) {
        return service.findDriversActiveRide(id);
    }

    @GetMapping("/active/{passengerId}")
    public Ride findPassengersActiveRide(@PathVariable Long id) {
        return service.findPassengersActiveRide(id);
    }

    @GetMapping("/{id}")
    public Ride findRideById(@PathVariable Long id) {
        return service.findRideById(id);
    }

    // promijeniti status voznje
    @PutMapping("/{id}")
    public boolean cancelExistingRide(@PathVariable Long id) {
        return service.cancelExistingRide(id);  //PRIJE NEGO VOZAC DODJE NA DESTINACIJU (to passenger moze)
    }

    //panic
    @PutMapping("/{id}/panic")
    public Message setPanic(@RequestBody String reason, @PathVariable Long id) {
        return service.setPanic(reason, id);
    }

    // prihvati voznju
    @PutMapping("/{id}/accept")
    public Ride acceptRide(@PathVariable Long id) {
        return service.acceptRide(id);
    }

    // prihvati voznju
    @PutMapping("/{id}/end")
    public Ride endRide(@PathVariable Long id) {
        return service.endRide(id);
    }

    @PutMapping("/{id}/cancel")
    public Ride cancelRide(@RequestBody String reason, @PathVariable Long id) {
        return service.cancelRide(reason, id);  //vozac moze da canceluje
    }
}
