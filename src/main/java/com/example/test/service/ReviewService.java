package com.example.test.service;

import com.example.test.domain.communication.Review;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReviewService implements IReviewService {

    @Override
    public Review insertVehicleReview(Long vehicleId, Review review) {
        return null;
    }

    @Override
    public Collection<Review> getReviewByVehicle(Long vehicleId) {
        return null;
    }

    @Override
    public Review insertDriverReview(Long driverId, Review review) {
        return null;
    }

    @Override
    public Collection<Review> getReviewByDriver(Long driverId) {
        return null;
    }

    @Override
    public Collection<Review> getReviewByRide(Long rideId) {
        return null;
    }
}
