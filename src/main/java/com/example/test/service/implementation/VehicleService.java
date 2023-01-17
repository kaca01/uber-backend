package com.example.test.service.implementation;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.vehicle.IVehicleRepository;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    IVehicleRepository vehicleRepository;

    @Override
    public Boolean update(Long id, Location location) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException("Vehicle does not exist!"));;
        vehicle.setCurrentLocation(location);
        return true;
    }
}
