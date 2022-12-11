package com.example.test.dto.ride;

import com.example.test.dto.ride.RideReviewDTO;

import java.util.ArrayList;

public class AllRideReviewsDTO {
    
    private ArrayList<RideReviewDTO> rideReviews;

    public AllRideReviewsDTO() {

    }

    // response

    public AllRideReviewsDTO(ArrayList<RideReviewDTO> rideReviews) {
        this.rideReviews = rideReviews;
    }

    public ArrayList<RideReviewDTO> getRideReviews() {
        return rideReviews;
    }

    public void setRideReviews(ArrayList<RideReviewDTO> rideReviews) {
        this.rideReviews = rideReviews;
    }

}
