package com.example.test.controller;

import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.ReviewDTO;
import com.example.test.dto.ride.RideReviewDTO;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private IReviewService service;

    // Creating a review about the vehicle
    @PreAuthorize("hasRole('PASSENGER')")
    @PostMapping(value = "/{rideId}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> insertVehicleReview(@PathVariable int rideId, @Valid @RequestBody ReviewDTO reviewDTO)
    {
        ReviewDTO review = service.insertVehicleReview((long) rideId, reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Get the reviews for the specific vehicle
    @GetMapping(value ="/vehicle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<ReviewDTO>> getReviewByVehicle(@PathVariable int id) {

        AllDTO<ReviewDTO> reviews = service.getReviewByVehicle((long) id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Creating a review about the driver
    @PreAuthorize("hasRole('PASSENGER')")
    @PostMapping(value = "/{rideId}/driver", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> insertDriverReview(@PathVariable Long rideId, @Valid @RequestBody ReviewDTO reviewDTO)
    {
        ReviewDTO review = service.insertDriverReview(rideId, reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Get the reviews for the specific driver
    @GetMapping(value ="/driver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<ReviewDTO>> getReviewByDriver(@PathVariable int id) {

        AllDTO<ReviewDTO> reviews = service.getReviewByDriver((long) id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Overview of both reviews for the specific ride (driver and vehicle)
    @GetMapping(value ="/{rideId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideReviewDTO>> getReviewByRide(@PathVariable int rideId) {
        List<RideReviewDTO> reviews = service.getReviewByRide((long) rideId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
