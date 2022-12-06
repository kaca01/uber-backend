package com.example.test.service;

import com.example.test.domain.business.WorkTime;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.DriverDocument;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DriverService implements IDriverService {

    @Override
    public Driver insert(Driver driver) {
        return null;
    }

    @Override
    public Collection<Driver> getAll() {
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
    public Collection<DriverDocument> getDriverDocuments(Long id) {
        return null;
    }

    @Override
    public Boolean deleteDriverDocument(Long id) {
        return false;
    }

    @Override
    public Collection<DriverDocument> insertDriverDocuments(Long id, Collection<DriverDocument> driverDocuments) {
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
    public WorkTime getWorkTime(Long id) {
        return null;
    }

    @Override
    public WorkTime insertWorkTime(Long id, WorkTime workTime) {
        return null;
    }

    @Override
    public Collection<Ride> getRides(Long id) {
        return null;
    }

    @Override
    public WorkTime getWorkTime(Long id, Long workTimeId) {
        return null;
    }

    @Override
    public WorkTime updateWorkTime(Long id, Long workTimeId) {
        return null;
    }
}
