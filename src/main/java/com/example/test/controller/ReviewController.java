package com.example.test.controller;

import com.example.test.domain.communication.Review;
import com.example.test.dto.AllReviewsDTO;
import com.example.test.dto.AllRideReviewsDTO;
import com.example.test.dto.ReviewDTO;
import com.example.test.dto.RideReviewDTO;
import com.example.test.enumeration.ReviewType;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private IReviewService service;

    // Creating a review about the vehicle
    @PostMapping(value = "/{rideId}/vehicle/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> insertVehicleReview(@PathVariable int rideId, @PathVariable int id,
                                                         @RequestBody ReviewDTO reviewDTO)
            throws Exception {

        Review review = new Review(reviewDTO);
        review = service.insertVehicleReview((long) rideId, (long) id, review);

        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400

        return new ResponseEntity<>(new ReviewDTO(review), HttpStatus.OK);
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
    public ResponseEntity<ReviewDTO> insertDriverReview(@PathVariable Long rideId, @PathVariable Long id,
                                                        @RequestBody ReviewDTO reviewDTO)
            throws Exception {

        Review review = new Review(reviewDTO);
        review = service.insertDriverReview(rideId, id, review);

        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // todo za 400
        return new ResponseEntity<>(new ReviewDTO(review), HttpStatus.OK);
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
    @GetMapping(value ="/{rideId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideReviewDTO>> getReviewByRide(@PathVariable int rideId) {

        // list sort by passenger id
        List<Review> reviews = service.getReviewByRide((long) rideId);
        List<RideReviewDTO> rideReviewDTO = new ArrayList<>();

        for (int i=0; i< reviews.size(); i++) {
            System.out.println(reviews.get(i).getComment());
            if((i+1)< reviews.size() && Objects.equals(reviews.get(i).getPassengerId(), reviews.get(i + 1).getPassengerId())) {
                if(reviews.get(i).getType() == ReviewType.DRIVER) {
                    rideReviewDTO.add(new RideReviewDTO(new ReviewDTO(reviews.get(i+1)), new ReviewDTO(reviews.get(i))));
                } else {
                    rideReviewDTO.add(new RideReviewDTO(new ReviewDTO(reviews.get(i)), new ReviewDTO(reviews.get(i+1))));
                }
                i = i+1;
            } else {
                System.out.println(reviews.get(i).getPassengerId());
                if(reviews.get(i).getType() == ReviewType.DRIVER) {
                    rideReviewDTO.add(new RideReviewDTO(null, new ReviewDTO(reviews.get(i))));
                } else {
                    rideReviewDTO.add(new RideReviewDTO(new ReviewDTO(reviews.get(i)), null));
                }
            }
        }
        return new ResponseEntity<>(rideReviewDTO, HttpStatus.OK);
    }
}
