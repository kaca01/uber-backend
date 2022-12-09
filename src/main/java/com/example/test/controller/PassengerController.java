package com.example.test.controller;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.*;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    @Autowired
    IPassengerService service;

    // create passenger
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO passengerDTO) throws Exception
    {
        Passenger passenger = new Passenger(passengerDTO);
        passenger = service.insert(passenger);  // returns passenger with set id
        if (passenger == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<UserDTO>(new UserDTO(passenger), HttpStatus.CREATED);
    }

    //getting passengers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllUsersDTO> getPassengers()
    {
        List<Passenger> passengers = service.getAll(0, 0);

        // convert passengers to DTOs
        ArrayList<UserDTO> passengersDTO = new ArrayList<>();
        for (Passenger p : passengers) {
            passengersDTO.add(new UserDTO(p));
        }

        return new ResponseEntity<AllUsersDTO>(new AllUsersDTO(passengersDTO.size(), passengersDTO), HttpStatus.OK);
    }

    //activate passenger account
    @GetMapping(value = "/activate/{activationId}")
    public ResponseEntity<Boolean> activatePassenger(@PathVariable int activationId) throws Exception
    {
        Boolean flag = service.activatePassenger((long) activationId);
        if (!flag) {
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        //TODO add error 400 (bad request=invalid data)
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    //get passsenger details
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findUserById(@PathVariable int id)
    {
        Passenger passenger = service.findUserById((long) id);

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<UserDTO>(new UserDTO(passenger), HttpStatus.OK);
    }

    //Update existing passenger
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO passengerDTO, @PathVariable int id) throws Exception
    {
        Passenger passenger = new Passenger(passengerDTO);

        passenger = service.update(passenger, (long) id);

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<UserDTO>(new UserDTO(passenger), HttpStatus.OK);
    }

    //get passenger rides
    //todo PAGINATED RIDES
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllRidesDTO> getRidesByPassenger(@PathVariable int id)
    {
        List<Ride> rides = service.getRidesByPassenger((long)id);

        // convert rides to DTOs
        ArrayList<RideDTO> ridesDTO = new ArrayList<>();
        for (Ride r : rides) {
            ridesDTO.add(new RideDTO(r));
        }

        //TODO error 400 and 404
        return new ResponseEntity<AllRidesDTO>(new AllRidesDTO(ridesDTO.size(), ridesDTO), HttpStatus.OK);
    }
}
