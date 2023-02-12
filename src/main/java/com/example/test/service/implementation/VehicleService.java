package com.example.test.service.implementation;

import com.example.test.domain.ride.Location;
import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.vehicle.VehicleDTO;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.ride.ILocationRepository;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.repository.vehicle.IVehicleRepository;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    IVehicleRepository vehicleRepository;
    @Autowired
    ILocationRepository iLocationRepository;
    @Autowired
    IDriverRepository iDriverRepository;

    @Override
    public Vehicle update(Long id, Location location) {
        Vehicle vehicle = this.vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException("Vehicle does not exist!"));
        location = iLocationRepository.save(location);
        vehicle.setCurrentLocation(location);
        return vehicleRepository.save(vehicle);
    }
}
