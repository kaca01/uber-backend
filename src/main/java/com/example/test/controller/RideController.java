package com.example.test.controller;

import com.example.test.domain.communication.Message;
import com.example.test.domain.ride.Ride;
import com.example.test.dto.PanicDTO;
import com.example.test.dto.RideDTO;
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

    //creating a ride
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> insert(@RequestBody RideDTO rideDTO) throws Exception
    {
        Ride newRide = new Ride(rideDTO);
        newRide = service.insert(newRide, rideDTO);  // returns ride with set id and other data

        // todo newRide will never be null. Error 404 should be sent when there is invalid data
        return new ResponseEntity<RideDTO>(new RideDTO(newRide), HttpStatus.OK);
    }

    //active ride for driver
    @GetMapping(value = "/driver/{driverId}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findDriversActiveRide(@PathVariable Long driverId)
    {
        Ride ride = service.findDriversActiveRide(driverId);
        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }

    //active ride for passenger
    @GetMapping(value = "/passenger/{passengerId}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findPassengersActiveRide(@PathVariable Long passengerId)
    {
        Ride ride = service.findPassengersActiveRide(passengerId);
        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }

    //ride details
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findRideById(@PathVariable Long id)
    {
        Ride ride = service.findRideById(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }

    // cancel existing ride (perspective of passenger - before the driver has arrived at the destination)
    // promijeniti status voznje
    @PutMapping(value = "/{id}/withdraw")
    public ResponseEntity<RideDTO> cancelExistingRide(@PathVariable Long id) throws Exception
    {
        Ride ride = service.cancelExistingRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }

    //panic button pressed
    @PutMapping(value = "/{id}/panic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PanicDTO> setPanic(@RequestBody String reason, @PathVariable Long id) throws Exception
    {
        Message message = service.setPanic(reason, id);

        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<PanicDTO>(new PanicDTO(message), HttpStatus.OK);
    }

    //accept the ride
    @PutMapping(value = "/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long id) throws Exception
    {
        Ride ride = service.acceptRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }

    //end the ride
    @PutMapping(value = "/{id}/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> endRide(@PathVariable Long id) throws Exception
    {
        Ride ride = service.endRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }

    //cancel the ride with an explanation (perspective of driver)
    @PutMapping(value = "/{id}/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> cancelRide(@RequestBody String reason, @PathVariable Long id)
    {
        Ride ride = service.cancelRide(reason, id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(new RideDTO(ride), HttpStatus.OK);
    }
}
