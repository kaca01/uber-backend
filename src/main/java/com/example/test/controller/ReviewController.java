package com.example.test.controller;

import com.example.test.domain.communication.Review;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private IReviewService service;

    // todo treba proemniti putanju inace ne radi
    @PostMapping(value = "/review/{vehicleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> insertVehicleReview(@PathVariable Long vehicleId, @RequestBody Review review) throws Exception {
        Review savedReview = service.insertVehicleReview(vehicleId, review);

        if(savedReview == null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Review>(savedReview, HttpStatus.CREATED);
    }

    // todo treba proemniti putanju inace ne radi
    @GetMapping(value ="/review/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Review>> getReviewByVehicle(@PathVariable Long vehicleId) {
        Collection<Review> reviews = service.getReviewByVehicle(vehicleId);
        return new ResponseEntity<Collection<Review>>(reviews, HttpStatus.OK);
    }

    // todo treba proemniti putanju inace ne radi
    @PostMapping(value = "/review/{driverId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> insertDriverReview(@PathVariable Long driverId, @RequestBody Review review) throws Exception {
        Review savedReview = service.insertDriverReview(driverId, review);

        if(savedReview == null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<Review>(savedReview, HttpStatus.CREATED);
    }

    // todo treba proemniti putanju inace ne radi
    @GetMapping(value ="/review/{driverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Review>> getReviewByDriver(@PathVariable Long driverId) {
        Collection<Review> reviews = service.getReviewByDriver(driverId);
        return new ResponseEntity<Collection<Review>>(reviews, HttpStatus.OK);
    }

    @GetMapping(value ="/reviews/{rideId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Review>> getReviewByRide(@PathVariable Long rideId) {
        Collection<Review> reviews = service.getReviewByRide(rideId);
        return new ResponseEntity<Collection<Review>>(reviews, HttpStatus.OK);
    }
}
