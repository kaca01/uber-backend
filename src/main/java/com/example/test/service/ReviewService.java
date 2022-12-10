package com.example.test.service;

import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.enumeration.ReviewType;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IReviewService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService implements IReviewService {

    private ArrayList<Passenger> passengers = createPassengers();
    private ArrayList<Review> reviews = createReview();
    private List<Vehicle> vehicles = createVehicle();
    private List<Driver> drivers = createDrivers();
    private List<Ride> rides = createRide();

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
    public List<Review> getReviewByRide(Long rideId) {
        List<Review> rideReviews = new ArrayList<>();
        for(Ride r : rides) {
            if(Objects.equals(r.getId(), rideId)) {
                rideReviews = r.getReviews();
            }
        }
        rideReviews.sort(Comparator.comparing(Review::getPassengerId));
        return rideReviews;
    }

    private ArrayList<Passenger> createPassengers()
    {
        Passenger p1 = new Passenger(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p2 = new Passenger(2L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p3 = new Passenger(3L, "Neko", "Nekic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p4 = new Passenger(4L, "Bosko", "Radojcic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        ArrayList<Passenger> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);
        return ps;
    }

    private ArrayList<Review> createReview()
    {
        Review v1 = new Review(1L, 3, "dirty vehicle", passengers.get(0), ReviewType.VEHICLE);
        Review v2 = new Review(2L, 5, "great vehicle", passengers.get(3), ReviewType.VEHICLE);
        Review v3 = new Review(3L, 1, "rude driver", passengers.get(2), ReviewType.DRIVER);
        Review v4 = new Review(4L, 5, "kind driver", passengers.get(1), ReviewType.DRIVER);
        Review v5 = new Review(5L, 5, "lovely ride", passengers.get(1), ReviewType.VEHICLE);
        ArrayList<Review> ps = new ArrayList<>();
        ps.add(v1);
        ps.add(v2);
        ps.add(v3);
        ps.add(v4);
        ps.add(v5);
        return ps;
    }

    private List<Vehicle> createVehicle()
    {
        Vehicle v1 = new Vehicle(1L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v2 = new Vehicle(2L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v3 = new Vehicle(3L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v4 = new Vehicle(4L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        List<Vehicle> ps = new ArrayList<>();
        ps.add(v1);
        ps.add(v2);
        ps.add(v3);
        ps.add(v4);
        return ps;
    }

    private List<Driver> createDrivers()
    {
        Driver p1 = new Driver(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, 7, null, vehicles.get(0));
        Driver p2 = new Driver(2L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, 4576, null, vehicles.get(1));
        Driver p3 = new Driver(3L, "Neko", "Nekic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, 65757, null, vehicles.get(2));
        Driver p4 = new Driver(4L, "Bosko", "Radojcic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true,4324345, null, vehicles.get(3));
        ArrayList<Driver> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);
        return ps;
    }

    private List<Ride> createRide()
    {
        Ride v1 = new Ride(1L, null, null, 98.8, 25.32, vehicles.get(1), drivers.get(3), passengers, reviews, null, null, null, true, false, null);
        Ride v2 = new Ride(2L, null, null, 328.8, 2234.76, vehicles.get(2), drivers.get(2), passengers, reviews, null, null, null, false, false, null);
        Ride v3 = new Ride(3L, null, null, 768.92, 32.54, vehicles.get(3), drivers.get(0), passengers, reviews, null, null, null, false, false, null);
        Ride v4 = new Ride(4L, null, null, 32.00, 18.02, vehicles.get(0), drivers.get(1), passengers, reviews, null, null, null, true, false, null);
        List<Ride> ps = new ArrayList<>();
        ps.add(v1);
        ps.add(v2);
        ps.add(v3);
        ps.add(v4);
        return ps;
    }
}
