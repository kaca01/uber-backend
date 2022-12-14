package com.example.test.service.interfaces;

import com.example.test.domain.communication.Review;

import java.util.List;
import java.util.Set;

public interface IReviewService {

    public Review insertVehicleReview(Long rideId, Long vehicleId, Review review);

    public List<Review> getReviewByVehicle(Long vehicleId);

    public Review insertDriverReview(Long rideId, Long driverId, Review review);

    public List<Review> getReviewByDriver(Long driverId);

    public Set<Review> getReviewByRide(Long rideId);
}
