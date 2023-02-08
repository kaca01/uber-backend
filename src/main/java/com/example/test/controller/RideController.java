package com.example.test.controller;

import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;
    @Autowired
    IPassengerRepository passengerRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    //creating a ride
    @PreAuthorize("hasAnyRole('PASSENGER', 'DRIVER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> insert(@Nullable @Valid @RequestBody RideDTO rideDTO) throws Exception
    {
        RideDTO newRide = service.insert(rideDTO);  // returns ride with set id and other data
        return new ResponseEntity<RideDTO>(newRide, HttpStatus.OK);
    }

    @GetMapping(value = "/nextRide/{driverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<RideDTO> getNextRide(@PathVariable Long driverId)
    {
        RideDTO newRide = service.getNextRide(driverId);
        return new ResponseEntity<RideDTO>(newRide, HttpStatus.OK);
    }

    //active ride for driver
    //also used for simulation
    @GetMapping(value = "/driver/{driverId}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findDriversActiveRide(@PathVariable Long driverId)
    {
        RideDTO ride = service.findDriversActiveRide(driverId);
        System.out.println(ride);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //active ride for passenger
    @PreAuthorize("hasAnyRole('PASSENGER', 'ADMIN')")
    @GetMapping(value = "/passenger/{passengerId}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findPassengersActiveRide(@PathVariable Long passengerId)
    {
        RideDTO ride = service.findPassengersActiveRide(passengerId);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('DRIVER')")
    @GetMapping(value = "/accepted/next/{driverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findNextAcceptedRide(@PathVariable Long driverId)
    {
        RideDTO ride = service.findNextAcceptedRide(driverId);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @GetMapping(value = "/driver/{driverId}/accepted", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findDriversAcceptedRide(@PathVariable Long driverId)
    {
        RideDTO ride = service.findDriversAcceptedRide(driverId);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping(value = "/accepted/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> checkAcceptedRide(@PathVariable Long id) {
        RideDTO ride = service.getAcceptedRide(id);
        return new ResponseEntity<>(ride, HttpStatus.OK);
    }

    //ride details
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER', 'DRIVER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> findOne(@PathVariable Long id)
    {
        RideDTO ride = service.findOne(id);

        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    // cancel existing ride (perspective of passenger - before the driver has arrived at the destination)
    @PreAuthorize("hasRole('PASSENGER')")
    @PutMapping(value = "/{id}/withdraw")
    public ResponseEntity<RideDTO> cancelExistingRide(@PathVariable Long id)
    {
        RideDTO ride = service.cancelExistingRide(id);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('PASSENGER', 'DRIVER')")
    //panic button pressed
    @PutMapping(value = "/{id}/panic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PanicDTO> setPanic(@Nullable @Valid @RequestBody PanicDTO reason, @PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User sender = userRepository.findByEmail(email).orElse(null);
        PanicDTO message = service.setPanic(reason, id, sender);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/map-updates/panic", this.service.findOne(id));
        return new ResponseEntity<PanicDTO>(message, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    //accept the ride
    @PutMapping(value = "/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long id)
    {
        RideDTO ride = service.acceptRide(id);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")  //ukloniti zbog simulacije
    @PutMapping(value = "/{id}/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> startRide(@PathVariable Long id) {

        RideDTO ride = service.startRide(id);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/map-updates/change-page-start", ride);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/map-updates/start-ride", ride);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    //end the ride
    @PutMapping(value = "/{id}/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> endRide(@PathVariable Long id)
    {
        RideDTO ride = service.endRide(id);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/map-updates/change-page-end", ride);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/map-updates/ended-ride", ride);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    //cancel the ride with an explanation (perspective of driver)
    @PutMapping(value = "/{id}/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<RideDTO> cancelRide(@Nullable @Valid @RequestBody RejectionDTO reason, @PathVariable Long id)
    {
        RideDTO ride = service.cancelRide(reason, id);
        return new ResponseEntity<RideDTO>(ride, HttpStatus.OK);
    }

    @PostMapping(value = "/favorites", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<FavoriteOrder> insertFavoriteLocation(@Nullable @Valid @RequestBody FavoriteOrder favoriteOrder)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Passenger p = passengerRepository.findByEmail(email);
        FavoriteOrder order = service.insertFavoriteOrder(favoriteOrder, email);

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
    public ResponseEntity<Void> deleteFavoriteLocation(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Passenger p = passengerRepository.findByEmail(email);
        service.deleteFavoriteLocation(id, p);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // checking if the driver or passenger has a pending ride
    @PreAuthorize("hasAnyRole('DRIVER', 'PASSENGER')")
    @GetMapping(value = "/pending/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> checkPendingRide(@PathVariable Long id) {
        RideDTO ride = service.getPendingRide(id);
        return new ResponseEntity<>(ride, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('DRIVER')")
//    @GetMapping(value = "/pending/{id}/driver", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<RideDTO> checkDriverPendingRide(@PathVariable Long id) {
//        RideDTO ride = service.getDriverPendingRide(id);
//        return new ResponseEntity<>(ride, HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping(value = "/pending/{id}/passenger", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> checkPassengerPendingRide(@PathVariable Long id) {
        RideDTO ride = service.getPassengerPendingRide(id);
        return new ResponseEntity<>(ride, HttpStatus.OK);
    }
}
