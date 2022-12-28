package com.example.test.repository.ride;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.enumeration.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IRideRepository extends JpaRepository<Ride, Long> {

    // TODO : check if this works?
    public List<Ride> findByPassengers_id(Long passengerId);

    public Ride findByStatusAndDriver_id(RideStatus status, Long driverId);

    public Ride findByStatusAndPassengers_id(RideStatus status, Long passengerId);

    public List<Ride> findRidesByDriverId(Long id);
}
