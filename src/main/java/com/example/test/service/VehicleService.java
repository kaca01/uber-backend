package com.example.test.service;

import com.example.test.Mockup;
import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    Mockup mockup = new Mockup();
    List<Vehicle> vehicles = mockup.vehicles;

    @Override
    public Boolean update(Long id, Location location) {
        Vehicle vehicle = findVehicleById(id);
        if (vehicle == null) { return false; }
        vehicle.setCurrentLocation(location);
        return true;
    }

    public Vehicle findVehicleById(Long id) {
        for(Vehicle v: vehicles)
        {
            if(v.getId().equals(id)) return v;
        }
        return null;
    }
}
