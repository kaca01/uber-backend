package com.example.test.service;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    private List<Vehicle> vehicles = creatVehicle();

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

    // creating dummy data
    private List<Vehicle> creatVehicle()
    {
        Vehicle v1 = new Vehicle(1L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v2 = new Vehicle(2L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v3 = new Vehicle(3L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v4 = new Vehicle(4L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        List<Vehicle> ps = new ArrayList<>();
        ps.add(v1);
        ps.add(v2);
        ps.add(v3);
        ps.add(v4);
        return ps;
    }
}
