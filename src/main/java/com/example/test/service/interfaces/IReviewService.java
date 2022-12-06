package com.example.test.service.interfaces;

import com.example.test.domain.communication.Review;

import java.util.Collection;

public interface IReviewService {

    public Review insertVehicleReview(Long rideId, Long vehicleId, Review review);

    public Collection<Review> getReviewByVehicle(Long vehicleId);

    public Review insertDriverReview(Long rideId, Long driverId, Review review);

    public Collection<Review> getReviewByDriver(Long driverId);

    public Collection<Review> getReviewByRide(Long rideId);
}
