package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> insert(@RequestBody Ride ride) throws Exception
    {
        Ride newRide = service.insert(ride);
        if (newRide == null)
            return new ResponseEntity<Ride>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Ride>(newRide, HttpStatus.CREATED);
    }

    @GetMapping(value = "/active/{driverId}/driver", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> findDriversActiveRide(@PathVariable Long id)
    {
        Ride ride = service.findDriversActiveRide(id);
        if (ride == null) {
            return new ResponseEntity<Ride>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Ride>(ride, HttpStatus.OK);
    }

    @GetMapping(value = "/active/{passengerId}/passenger", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> findPassengersActiveRide(@PathVariable Long id)
    {
        Ride ride = service.findPassengersActiveRide(id);

        if (ride == null) {
            return new ResponseEntity<Ride>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Ride>(ride, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> findRideById(@PathVariable Long id)
    {
        Ride ride = service.findRideById(id);

        if (ride == null) {
            return new ResponseEntity<Ride>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<Ride>(ride, HttpStatus.OK);
    }

    // promijeniti status voznje
    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> cancelExistingRide(@PathVariable Long id) throws Exception
    {
        Boolean flag = service.cancelExistingRide(id);  //PRIJE NEGO VOZAC DODJE NA DESTINACIJU (to passenger moze)

        if (!flag) {
            return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
        }
        //TODO error 400
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/panic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> setPanic(@RequestBody String reason, @PathVariable Long id) throws Exception
    {
        Message message = service.setPanic(reason, id);

        if (message == null) {
            return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> acceptRide(@PathVariable Long id) throws Exception
    {
        Ride ride = service.acceptRide(id);

        if (ride == null) {
            return new ResponseEntity<Ride>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<Ride>(ride, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> endRide(@PathVariable Long id) throws Exception
    {
        Ride ride = service.endRide(id);

        if (ride == null) {
            return new ResponseEntity<Ride>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<Ride>(ride, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ride> cancelRide(@RequestBody String reason, @PathVariable Long id)
    {
        Ride ride = service.cancelRide(reason, id);  //vozac moze da canceluje
        if (ride == null) {
            return new ResponseEntity<Ride>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<Ride>(ride, HttpStatus.OK);
    }
}
