package com.example.test.service.interfaces;

import com.example.test.domain.communication.Review;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.ReviewDTO;
import com.example.test.dto.ride.RideReviewDTO;

import java.util.List;
import java.util.Set;

public interface IReviewService {

    public ReviewDTO insertVehicleReview(Long rideId, ReviewDTO review);

    public AllDTO<ReviewDTO> getReviewByVehicle(Long vehicleId);

    public ReviewDTO insertDriverReview(Long rideId, ReviewDTO review);

    public AllDTO<ReviewDTO> getReviewByDriver(Long driverId);

    public List<RideReviewDTO> getReviewByRide(Long rideId);
}
