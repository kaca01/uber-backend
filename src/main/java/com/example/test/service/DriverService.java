package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.user.User;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.repository.business.WorkingHourRepository;
import com.example.test.repository.ride.LocationRepository;
import com.example.test.repository.ride.RideRepository;
import com.example.test.repository.user.DocumentRepository;
import com.example.test.repository.user.DriverRepository;
import com.example.test.repository.user.UserRepository;
import com.example.test.repository.vehicle.VehicleRepository;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DriverService implements IDriverService {

    @Autowired
    DriverRepository driverRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    RideRepository rideRepository;

    @Autowired
    WorkingHourRepository workingHourRepository;

    @Override
    public Driver insert(Driver driver) {
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver get(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    @Transactional
    public Driver update(Long id, Driver driver) {
        driver.setId(id);
        Driver foundDriver = get(id);
        if (foundDriver == null) return null;
        userRepository.save(driver);
        return driver;
    }

    @Override
    public List<Document> getDriverDocuments(Long id) {
        Driver driver = get(id);
        return documentRepository.findDocumentsByDriverId(driver);
    }

    @Override
    public Document insertDriverDocument(Long id, Document document) {
        Driver driver = get(id);
        if (driver == null) return null;
        document.setDriver(driver);
        return documentRepository.save(document);
    }

    @Override
    @Transactional
    public Document deleteDriverDocument(Long id) {
        Document document = documentRepository.findById(id);
        if (document == null) return null;
        documentRepository.deleteDocumentById(id);
        return document;
    }

    @Override
    public Vehicle getVehicle(Long id) {
        Vehicle vehicle = driverRepository.findVehicleIdByDriverId(id);
        return vehicle;
    }

    @Override
    public Vehicle insertVehicle(Long id, Vehicle vehicle) {
        Driver driver = get(id);
        if (driver == null) return null;
        vehicle = saveLocation(vehicle);
        vehicleRepository.save(vehicle);
        driver.setVehicle(vehicle);
        driverRepository.save(driver);
        return vehicle;
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Driver driver = get(id);
        if (driver == null) return null;
        vehicle = saveLocation(vehicle);
        driver.setVehicle(vehicle);
        vehicleRepository.save(vehicle);
        driverRepository.save(driver);
        return vehicle;
    }

    @Override
    public Set<WorkingHour> getWorkTimes(Long id) {
        Driver driver = get(id);
        if (driver == null) return null;
        return driver.getWorkingHours();
    }

    @Override
    public WorkingHour insertWorkTime(Long id, WorkingHour workingHour) {
        Driver driver = get(id);
        if (driver == null) return null;
        driver.getWorkingHours().add(workingHour);
        driverRepository.save(driver);
        return workingHour;
    }

    @Override
    public List<Ride> getRides(Long id) {
        Driver driver = get(id);
        if (driver == null) return null;
        return rideRepository.findRidesByDriverId(id);
    }

    @Override
    public WorkingHour getWorkTime(Long workTimeId) {
        return workingHourRepository.findById(workTimeId);
    }

    @Override
    public WorkingHour updateWorkTime(Long workTimeId, WorkingHour workingHour) {
        if (workingHourRepository.findById(workTimeId) == null) return null;
        workingHour.setId(workTimeId);
        workingHourRepository.save(workingHour);
        return workingHour;
    }

    public Vehicle saveLocation(Vehicle vehicle) {
        // TODO : create function for this in location service after everyone finishes services
        // we are not here just saving location because there can be locations with the same longitude and
        // latitude but different address (address is a string so it is tricky)
        // check if there is current location in database
        Location currentLocation = vehicle.getCurrentLocation();
        Location location = locationRepository.findByLatitudeAndLongitude(currentLocation.getLatitude(),
                currentLocation.getLongitude());
        // if there is not, save it
        if (location == null) locationRepository.save(currentLocation);
            // if there is set location from database
        else vehicle.setCurrentLocation(location);
        return vehicle;
    }
}