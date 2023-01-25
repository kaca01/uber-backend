package com.example.test.controller;

import com.example.test.dto.AllDTO;
import com.example.test.dto.ErrorDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/passenger")
public class PassengerController {

    @Autowired
    IPassengerService service;

    // create passenger
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO passengerDTO)
    {
        UserDTO passenger = service.insert(passengerDTO);  // returns passenger with set id
        return new ResponseEntity<UserDTO>(passenger, HttpStatus.OK);
    }

    //getting passengers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllDTO<UserDTO>> getPassengers()
    {
        List<UserDTO> passengersDTO = service.getAll(0, 0);

        AllDTO<UserDTO> allUsers = new AllDTO<>(passengersDTO.size(), passengersDTO);

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //activate passenger account
    @GetMapping(value = "/activate/{activationId}")
    public ResponseEntity<ErrorDTO> activatePassenger(@PathVariable int activationId)
    {
        ErrorDTO message = service.activatePassenger((long) activationId);
        return new ResponseEntity<ErrorDTO>(message, HttpStatus.OK);
    }

    //get passsenger details
    @PreAuthorize("hasAnyRole('PASSENGER', 'DRIVER', 'ADMIN')")  //testovi ne zele da admin ima pristup??
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findOne(@PathVariable int id)
    {
        UserDTO passenger = service.findOne((long) id);
        return new ResponseEntity<UserDTO>(passenger, HttpStatus.OK);
    }

    //Update existing passenger
    @PreAuthorize("hasRole('PASSENGER')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO passengerDTO, @PathVariable int id)
    {
        UserDTO passenger = service.update(passengerDTO, (long) id);
        return new ResponseEntity<UserDTO>(passenger, HttpStatus.OK);
    }

    //get passenger rides
    //todo PAGINATED RIDES
    @PreAuthorize("hasAnyRole('PASSENGER', 'ADMIN')")
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRidesByPassenger(@PathVariable int id)
    {
        List<RideDTO> rides = service.getRidesByPassenger((long)id);

        AllDTO<RideDTO> allRides = new AllDTO<>(rides.size(), rides);
        return new ResponseEntity<>(allRides, HttpStatus.OK);
    }
}
