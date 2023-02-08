package com.example.test.service.implementation;

import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.ReviewDTO;
import com.example.test.dto.ride.RideReviewDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.ReviewType;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.communication.IReviewRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.repository.vehicle.IVehicleRepository;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    IReviewRepository reviewRepository;
    @Autowired
    IRideRepository rideRepository;
    @Autowired
    IVehicleRepository vehicleRepository;
    @Autowired
    IDriverRepository driverRepository;
    @Autowired
    IPassengerRepository passengerRepository;

    @Override
    @Transactional
    public ReviewDTO insertVehicleReview(Long rideId, ReviewDTO reviewDTO) {
        return insertReview(rideId,reviewDTO, ReviewType.VEHICLE);
    }

    @Override
    public AllDTO<ReviewDTO> getReviewByVehicle(Long vehicleId) {
        List<Review> vehicleReviews = new ArrayList<>();

        vehicleRepository.findById(vehicleId).orElseThrow(() -> new NotFoundException("Vehicle does not exist!"));

        List<Ride> rides = rideRepository.findRidesByVehicleId(vehicleId);
//        for(Ride ride: rides) {
//            for(Review review : rideRepository.findReviewsByRideId(ride.getId())) {
//                if(review.getType() == ReviewType.VEHICLE)
//                    vehicleReviews.add(review);
//            }
//        }
        List<ReviewDTO> reviewsDTO = new ArrayList<>();
        for(Review review : vehicleReviews) {
            reviewsDTO.add(new ReviewDTO(review));
        }
        return new AllDTO<>(reviewsDTO.size(), reviewsDTO);
    }

    @Override
    @Transactional
    public ReviewDTO insertDriverReview(Long rideId, ReviewDTO reviewDTO) {
        return insertReview(rideId, reviewDTO, ReviewType.DRIVER);
    }

    @Override
    public AllDTO<ReviewDTO> getReviewByDriver(Long driverId) {
        List<Review> vehicleReviews = new ArrayList<>();

        Driver driver = driverRepository.findById(driverId);
        if(driver == null) throw new NotFoundException("Driver does not exist!");

        List<Ride> rides = rideRepository.findRidesByDriverId(driverId);
//        for(Ride ride: rides) {
//            for(Review review : rideRepository.findReviewsByRideId(ride.getId())) {
//                if(review.getType() == ReviewType.DRIVER)
//                    vehicleReviews.add(review);
//            }
//        }
        List<ReviewDTO> reviewsDTO = new ArrayList<>();
        for(Review review : vehicleReviews) {
            reviewsDTO.add(new ReviewDTO(review));
        }
        return new AllDTO<>(reviewsDTO.size(), reviewsDTO);
    }

    @Override
    @Transactional
    public List<RideReviewDTO> getReviewByRide(Long rideId) {
        rideRepository.findById(rideId).orElseThrow(() -> new NotFoundException("Ride does not exist!"));

        List<Review> reviews = new ArrayList<>();
        //rideRepository.findReviewsByRideId(rideId);
        reviews.sort(Comparator.comparing(Review::getPassengerId));

        return convertReviewToRideReviewDTO(reviews);
    }

    private List<RideReviewDTO> convertReviewToRideReviewDTO(List<Review> reviews) {
        List<RideReviewDTO> rideReviewDTO = new ArrayList<>();

        for (int i=0; i < reviews.size(); i++) {
            // the passenger rated both the driver and the vehicle
            if((i+1) < reviews.size() &&
                    Objects.equals(reviews.get(i).getPassengerId(), reviews.get(i + 1).getPassengerId())) {
                // set driver review
                if(reviews.get(i).getType() == ReviewType.DRIVER)
                    rideReviewDTO.add(new RideReviewDTO(new ReviewDTO(reviews.get(i+1)), new ReviewDTO(reviews.get(i))));
                // set vehicle review
                else
                    rideReviewDTO.add(new RideReviewDTO(new ReviewDTO(reviews.get(i)), new ReviewDTO(reviews.get(i+1))));
                i = i+1;
            } else {
                // the passenger did not rate the driver
                if(reviews.get(i).getType() == ReviewType.DRIVER)
                    rideReviewDTO.add(new RideReviewDTO(null, new ReviewDTO(reviews.get(i))));
                // the passenger did not rate the vehicle
                else
                    rideReviewDTO.add(new RideReviewDTO(new ReviewDTO(reviews.get(i)), null));
            }
        }
        return rideReviewDTO;
    }

    private ReviewDTO  insertReview(Long rideId, ReviewDTO reviewDTO, ReviewType type) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new NotFoundException("Ride does not exist!"));
        Review review = new Review(reviewDTO);

        // get passenger from token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Passenger passenger = passengerRepository.findByEmail(email);
        review.setPassenger(passenger);

        review.setType(type);

        Set<Review> reviews = ride.getReviews();
        reviews.add(review);
        ride.setReviews(reviews);

        rideRepository.save(ride);

        reviewDTO.setId(review.getId());
        reviewDTO.setPassenger(new UserDTO(review.getPassenger()));
        return reviewDTO;
    }
}
