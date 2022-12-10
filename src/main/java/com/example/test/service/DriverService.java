package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DriverService implements IDriverService {

    @Override
    public Driver insert(Driver driver) {
        return null;
    }

    @Override
    public List<Driver> getAll() {
        return null;
    }

    @Override
    public Driver get(Long id) {
        return null;
    }

    @Override
    public Driver update(Long id, Driver driver) {
        return null;
    }

    @Override
    public List<Document> getDriverDocuments(Long id) {
        return null;
    }

    @Override
    public Boolean deleteDriverDocument(Long id) {
        return false;
    }

    @Override
    public Document insertDriverDocument(Long id, Document document) {
        return null;
    }

    @Override
    public Vehicle getVehicle(Long id) {
        return null;
    }

    @Override
    public Vehicle insertVehicle(Long id, Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        return null;
    }

    @Override
    public List<WorkingHour> getWorkTime(Long id) {
        return null;
    }

    @Override
    public WorkingHour insertWorkTime(Long id, WorkingHour workingHour) {
        return null;
    }

    @Override
    public List<Ride> getRides(Long id) {
        return null;
    }

    @Override
    public WorkingHour getWorkTime(Long workTimeId, boolean flag) {
        return null;
    }

    @Override
    public WorkingHour updateWorkTime(Long workTimeId) {
        return null;
    }
}