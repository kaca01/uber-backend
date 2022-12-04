package com.example.test.service.interfaces;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;

public interface IVehicleService {

    public Boolean update(Long id, Location location);
}
