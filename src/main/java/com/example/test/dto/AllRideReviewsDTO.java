package com.example.test.dto;

import java.util.ArrayList;

public class AllRideReviewsDTO {

    private class RideReview {
        private ReviewDTO vehicleReview;
        private ReviewDTO driverReview;

        public RideReview(ReviewDTO vehicleReview, ReviewDTO driverReview) {
            this.vehicleReview = vehicleReview;
            this.driverReview = driverReview;
        }

        public ReviewDTO getVehicleReview() {
            return vehicleReview;
        }

        public void setVehicleReview(ReviewDTO vehicleReview) {
            this.vehicleReview = vehicleReview;
        }

        public ReviewDTO getDriverReview() {
            return driverReview;
        }

        public void setDriverReview(ReviewDTO driverReview) {
            this.driverReview = driverReview;
        }


    }

    private ArrayList<RideReview> rideReviews;

    // response
    public AllRideReviewsDTO() {

    }

    public AllRideReviewsDTO(ArrayList<RideReview> rideReviews) {
        this.rideReviews = rideReviews;
    }

    public ArrayList<RideReview> getRideReviews() {
        return rideReviews;
    }

    public void setRideReviews(ArrayList<RideReview> rideReviews) {
        this.rideReviews = rideReviews;
    }
}
