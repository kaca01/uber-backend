package com.example.test.controller;

import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;
    @Autowired
    IPassengerRepository passengerRepository;

    //creating a ride
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> insert(@RequestBody RideDTO rideDTO) throws Exception
    {
        RideDTO newRide = service.insert(rideDTO);  // returns ride with set id and other data

        // todo newRide will never be null. Error 404 should be sent when there is invalid data
        return new ResponseEntity<RideDTO>(newRide, HttpStatus.OK);
    }

    //active ride for driver
    @GetMapping(value = "/driver/{driverId}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findDriversActiveRide(@PathVariable Long driverId)
    {
        RideDTO ride = service.findDriversActiveRide(driverId);
        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //active ride for passenger
    @GetMapping(value = "/passenger/{passengerId}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findPassengersActiveRide(@PathVariable Long passengerId)
    {
        RideDTO ride = service.findPassengersActiveRide(passengerId);
        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //ride details
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findOne(@PathVariable Long id)
    {
        RideDTO ride = service.findOne(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    // cancel existing ride (perspective of passenger - before the driver has arrived at the destination)
    @PutMapping(value = "/{id}/withdraw")
    public ResponseEntity<RideDTO> cancelExistingRide(@PathVariable Long id) throws Exception
    {
        RideDTO ride = service.cancelExistingRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //panic button pressed
    @PutMapping(value = "/{id}/panic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PanicDTO> setPanic(@RequestBody PanicDTO reason, @PathVariable Long id) throws Exception
    {
        PanicDTO message = service.setPanic(reason, id);

        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //TODO error 400
        return new ResponseEntity<PanicDTO>(message, HttpStatus.OK);
    }

    //accept the ride
    @PutMapping(value = "/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long id) throws Exception
    {
        RideDTO ride = service.acceptRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //start the ride
    @PutMapping(value = "/{id}/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> startRide(@PathVariable Long id) throws Exception
    {
        RideDTO ride = service.startRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //end the ride
    @PutMapping(value = "/{id}/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> endRide(@PathVariable Long id) throws Exception
    {
        RideDTO ride = service.endRide(id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //cancel the ride with an explanation (perspective of driver)
    @PutMapping(value = "/{id}/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> cancelRide(@RequestBody PanicDTO reason, @PathVariable Long id)
    {
        RideDTO ride = service.cancelRide(reason, id);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //todo error 400
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/favorites", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<FavoriteOrder> insertFavoriteLocation(@RequestBody FavoriteOrder favoriteOrder) throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Passenger p = passengerRepository.findByEmail(email);
        FavoriteOrder order = service.insertFavoriteOrder(favoriteOrder, email);

        if (order == null) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}

        return new ResponseEntity<FavoriteOrder>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<AllDTO<FavoriteOrder>> getFavoriteLocations()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Passenger p = passengerRepository.findByEmail(email);
        AllDTO<FavoriteOrder> allOrders = service.getFavoriteOrdersByPassenger(p);

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/favorites/{id}")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<Void> deleteFavoriteLocation(@PathVariable Long id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Passenger p = passengerRepository.findByEmail(email);
        boolean successful = service.deleteFavoriteLocation(id, p);

        if (!successful) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
