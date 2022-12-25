package com.example.test.repository;

import com.example.test.domain.ride.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRideRepository extends JpaRepository<Ride, Long> {

    public List<Ride> findByPassengers_id(Long passengerId);

}
