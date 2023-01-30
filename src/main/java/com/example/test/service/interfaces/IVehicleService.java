package com.example.test.service.interfaces;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.vehicle.VehicleDTO;

public interface IVehicleService {

    public Vehicle update(Long id, Location location);
}
