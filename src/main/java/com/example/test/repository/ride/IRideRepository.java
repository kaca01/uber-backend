package com.example.test.repository.ride;

import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.enumeration.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface IRideRepository extends JpaRepository<Ride, Long> {

    public List<Ride> findByPassengers_id(Long passengerId);

    public Optional<Ride> findByStatusAndDriver_id(RideStatus status, Long driverId);

    public Optional<Ride> findByStatusAndPassengers_id(RideStatus status, Long passengerId);
    public List<Ride> findRidesByStatusAndPassengers_email(RideStatus status, String email);

    public List<Ride> findRidesByDriverId(Long id);

    public List<Ride> findRidesByVehicleId(Long id);

    public List<Ride> findRidesByStatusAndDriver_Id(RideStatus status, Long driverId);
    public List<Ride> findRidesByStatusAndPassengers_Id(RideStatus status, Long driverId);
    List<Ride> findByStatus(RideStatus status);
}
