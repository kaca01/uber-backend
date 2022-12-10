package com.example.test.controller;

import com.example.test.domain.communication.Review;
import com.example.test.dto.AllReviewsDTO;
import com.example.test.dto.AllRideReviewsDTO;
import com.example.test.dto.ReviewDTO;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private IReviewService service;

    // Creating a review about the vehicle
    @PostMapping(value = "/{rideId}/vehicle/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> insertVehicleReview(@PathVariable Long rideId, @PathVariable Long id, @RequestBody ReviewDTO reviewDTO)
            throws Exception {

        Review review = new Review(reviewDTO);
        review = service.insertVehicleReview(rideId, id, review);

        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400

        return new ResponseEntity<>(new ReviewDTO(review), HttpStatus.CREATED);
    }

    // Get the reviews for the specific vehicle
    @GetMapping(value ="/vehicle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllReviewsDTO> getReviewByVehicle(@PathVariable int id) {

        List<Review> reviews = service.getReviewByVehicle((long) id);

        List<ReviewDTO> reviewsDTO = new ArrayList<>();
        for(Review review : reviews) {
            reviewsDTO.add(new ReviewDTO(review));
        }
        return new ResponseEntity<>(new AllReviewsDTO(reviewsDTO.size(), reviewsDTO), HttpStatus.OK);
    }

    // Creating a review about the driver
    @PostMapping(value = "/{rideId}/driver/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> insertDriverReview(@PathVariable Long rideId, @PathVariable Long id, @RequestBody ReviewDTO reviewDTO)
            throws Exception {

        Review review = new Review(reviewDTO);
        review = service.insertDriverReview(rideId, id, review);

        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new ReviewDTO(review), HttpStatus.CREATED);
    }

    // Get the reviews for the specific driver
    @GetMapping(value ="/driver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllReviewsDTO> getReviewByDriver(@PathVariable int id) {

        List<Review> reviews = service.getReviewByDriver((long) id);

        List<ReviewDTO> reviewsDTO = new ArrayList<>();
        for(Review review : reviews) {
            reviewsDTO.add(new ReviewDTO(review));
        }
        return new ResponseEntity<>(new AllReviewsDTO(reviewsDTO.size(), reviewsDTO), HttpStatus.OK);
    }

    // Overview of both reviews for the specific ride (driver and vehicle)
//    @GetMapping(value ="/{rideId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AllRideReviewsDTO> getReviewByRide(@PathVariable Long rideId) {
//
//        List<Review> reviews = service.getReviewByRide(rideId);
//
//
//        return new ResponseEntity<>(reviews, HttpStatus.OK);
//    }
        }
}
