package com.example.test.service;

import com.example.test.domain.communication.Review;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ReviewService implements IReviewService {

    @Override
    public Review insertVehicleReview(Long rideId, Long vehicleId, Review review) {
        return null;
    }

    @Override
    public List<Review> getReviewByVehicle(Long vehicleId) {
        return null;
    }

    @Override
    public Review insertDriverReview(Long rideId, Long driverId, Review review) {
        return null;
    }

    @Override
    public List<Review> getReviewByDriver(Long driverId) {
        return null;
    }

    @Override
    public List<Review> getReviewByRide(Long rideId) {
        // 1. naci sve review-ove za tu voznju iz baze
        // 2. kada se dobave svi review-ovi preko reviewType odrediti da li je za vehicle ili driver-a
        // 3. kreirati RideReviewDTO gde su review-ovi za vehicle i drivera grupisni
        return null;
    }
}
