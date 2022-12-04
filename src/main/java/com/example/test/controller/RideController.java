package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride insert(@RequestBody Ride ride)
    {
        return service.insert(ride);
    }

    @GetMapping(value = "/active/{driverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride findDriversActiveRide(@PathVariable Long id)
    {
        return service.findDriversActiveRide(id);
    }

    @GetMapping(value = "/active/{passengerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride findPassengersActiveRide(@PathVariable Long id)
    {
        return service.findPassengersActiveRide(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride findRideById(@PathVariable Long id)
    {
        return service.findRideById(id);
    }

    // promijeniti status voznje
    @PutMapping(value = "/{id}")  //todo vraca boolean,, da li treba producer?
    public boolean cancelExistingRide(@PathVariable Long id)
    {
        return service.cancelExistingRide(id);  //PRIJE NEGO VOZAC DODJE NA DESTINACIJU (to passenger moze)
    }

    //panic
    @PutMapping(value = "/{id}/panic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message setPanic(@RequestBody String reason, @PathVariable Long id)
    {
        return service.setPanic(reason, id);
    }

    // prihvati voznju
    @PutMapping(value = "/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride acceptRide(@PathVariable Long id)
    {
        return service.acceptRide(id);
    }

    // prihvati voznju
    @PutMapping(value = "/{id}/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride endRide(@PathVariable Long id)
    {
        return service.endRide(id);
    }

    @PutMapping(value = "/{id}/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride cancelRide(@RequestBody String reason, @PathVariable Long id)
    {
        return service.cancelRide(reason, id);  //vozac moze da canceluje
    }
}
