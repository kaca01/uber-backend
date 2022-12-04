package com.example.test.service;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UnregisteredUserService implements IUnregisteredUserService {

    @Override
    public Collection<Double> getEstimationTimeAndCost(Collection<Location> locations, Vehicle vehicle) {
        return null;
    }
}
