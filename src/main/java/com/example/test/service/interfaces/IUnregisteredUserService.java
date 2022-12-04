package com.example.test.service.interfaces;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;

import java.util.Collection;

public interface IUnregisteredUserService {

    public Collection<Double> getEstimationTimeAndCost(Collection<Location> locations, Vehicle vehicle);
}
