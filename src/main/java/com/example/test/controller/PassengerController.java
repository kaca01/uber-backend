package com.example.test.controller;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.AllDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
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
        UserDTO passenger = service.insert(passengerDTO);  // returns passenger with set id
        if (passenger == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // todo passenger will never be null. This request should be sent when there is invalid data
        return new ResponseEntity<UserDTO>(passenger, HttpStatus.OK);
    }

    //getting passengers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<UserDTO>> getPassengers()
    {
        List<UserDTO> passengersDTO = service.getAll(0, 0);

        AllDTO<UserDTO> allUsers = new AllDTO<>(passengersDTO.size(), passengersDTO);

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
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
    public ResponseEntity<UserDTO> findOne(@PathVariable int id)
    {
        UserDTO passenger = service.findOne((long) id);

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<UserDTO>(passenger, HttpStatus.OK);
    }

    //Update existing passenger
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO passengerDTO, @PathVariable int id) throws Exception
    {
        UserDTO passenger = service.update(passengerDTO, (long) id);

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<UserDTO>(passenger, HttpStatus.OK);
    }

    //get passenger rides
    //todo PAGINATED RIDES
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRidesByPassenger(@PathVariable int id)
    {
        List<RideDTO> rides = service.getRidesByPassenger((long)id);

        // ako ne postoji korisnik. Ako postoji korisnik a nema voznji, vraca praznu listu
        if (rides == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400

        AllDTO<RideDTO> allRides = new AllDTO<>(rides.size(), rides);
        return new ResponseEntity<>(allRides, HttpStatus.OK);
    }
}
