package com.example.test.service;

import com.example.test.domain.ride.Location;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    @Override
    public Boolean update(Long id, Location location) {
        return null;
    }
}
