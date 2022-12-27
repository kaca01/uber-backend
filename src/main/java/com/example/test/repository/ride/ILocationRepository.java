package com.example.test.repository.ride;

import com.example.test.domain.ride.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<Location, Integer> {

    public Location findByLatitudeAndLongitude(double latitude, double longitude);
}
