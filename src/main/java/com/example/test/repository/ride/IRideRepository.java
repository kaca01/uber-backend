package com.example.test.repository.ride;

import com.example.test.domain.ride.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRideRepository extends JpaRepository<Ride, Integer> {

    public List<Ride> findRidesByDriverId(Long id);
}
