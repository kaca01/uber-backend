package com.example.test.service;

import com.example.test.Mockup;
import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.enumeration.ReviewType;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService implements IReviewService {

    Mockup mockup = new Mockup();
    ArrayList<Passenger> passengers = mockup.passengers;
    ArrayList<Review> reviews = mockup.reviews;
    List<Ride> rides = mockup.rides;

    @Override
    public Review insertVehicleReview(Long rideId, Long vehicleId, Review review) {
        review.setId((reviews.get(reviews.size()-1).getId()+1));
        review.setPassenger(passengers.get(0));
        return review;
    }

    @Override
    public List<Review> getReviewByVehicle(Long vehicleId) {
        List<Review> vehicleReviews = new ArrayList<>();
        for(Ride r : rides) {
            if(Objects.equals(r.getVehicle().getId(), vehicleId)) {
                for (Review review : r.getReviews()) {
                    if(review.getType()==ReviewType.VEHICLE) {
                        vehicleReviews.add(review);
                    }
                }
            }
        }
        return vehicleReviews;
    }

    @Override
    public Review insertDriverReview(Long rideId, Long driverId, Review review) {
        review.setId((reviews.get(reviews.size()-1).getId()+1));
        review.setPassenger(passengers.get(0));
        return review;
    }

    @Override
    public List<Review> getReviewByDriver(Long driverId) {
        List<Review> driverReviews = new ArrayList<>();
        for(Ride r : rides) {
            if(Objects.equals(r.getVehicle().getId(), driverId)) {
                for (Review review : r.getReviews()) {
                    if(review.getType()==ReviewType.DRIVER) {
                        driverReviews.add(review);
                    }
                }
            }
        }
        return driverReviews;
    }

    @Override
    public Set<Review> getReviewByRide(Long rideId) {
        Set<Review> rideReviews = new HashSet<>();
        for(Ride r : rides) {
            if(Objects.equals(r.getId(), rideId)) {
                rideReviews = r.getReviews();
            }
        }
        //rideReviews.sort(Comparator.comparing(Review::getPassengerId));
        return rideReviews;
    }
}
