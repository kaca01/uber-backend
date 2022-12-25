package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.repository.user.DocumentRepository;
import com.example.test.repository.user.DriverRepository;
import com.example.test.repository.user.UserRepository;
import com.example.test.repository.vehicle.VehicleRepository;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Driver update(Long id, Driver driver) {
        // TODO for what is this id?
        userRepository.save(driver);
        driverRepository.save(driver);
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

    // TODO : this doesn't work
    @Override
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
    // TODO : this is where I need to continue
    @Override
    public Vehicle insertVehicle(Long id, Vehicle vehicle) {
        Driver driver = get(id);
        if (driver == null) return null;
        driver.setVehicle(vehicle);
        driverRepository.save(driver);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Driver driver = get(id);
        if (driver == null) return null;
        driver.setVehicle(vehicle);
        driverRepository.save(driver);
        vehicleRepository.save(vehicle);
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
        return workingHour;
    }

    @Override
    public List<Ride> getRides(Long id) {
//        Driver d = get(id);
//        if (d == null) return null;
//        List<Ride> foundRides = new ArrayList<>();
//        for (Ride ride : rides) {
//            if (ride.getDriver().getId().equals(id)) foundRides.add(ride);
//        }
        return null;
    }

    @Override
    public WorkingHour getWorkTime(Long workTimeId) {
//        for (WorkingHour workTime : workTimes) {
//            if (workTime.getId().equals(workTimeId)) return workTime;
//        }
        return null;
    }

    @Override
    public WorkingHour updateWorkTime(Long workTimeId, WorkingHour workingHour) {
//        for (WorkingHour workTime : workTimes) {
//            if (workTime.getId().equals(workTimeId)) {
//                workTime.setStart(workingHour.getStart());
//                workTime.setEnd(workingHour.getEnd());
//                return workTime;
//            }
//        }
        return null;
    }
}