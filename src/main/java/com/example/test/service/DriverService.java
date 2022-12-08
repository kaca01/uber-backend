package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.*;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DriverService implements IDriverService {

    @Override
    public UserDTO insert(UserDTO driver) {
        // TODO create and add to the database and after that return UserDTO
        return null;
    }

    @Override
    public AllUsersDTO getAll() {
        return null;
    }

    @Override
    public UserDTO get(Long id) {
        return null;
    }

    @Override
    public UserDTO update(Long id, UserDTO driver) {
        return null;
    }

    @Override
    public Collection<DocumentDTO> getDriverDocuments(Long id) {
        return null;
    }

    @Override
    public Boolean deleteDriverDocument(Long id) {
        return false;
    }

    @Override
    public DocumentDTO insertDriverDocument(Long id, DocumentDTO document) {
        return null;
    }

    @Override
    public VehicleDTO getVehicle(Long id) {
        return null;
    }

    @Override
    public VehicleDTO insertVehicle(Long id, VehicleDTO vehicle) {
        return null;
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicle) {
        return null;
    }

    @Override
    public AllWorkingHoursDTO getWorkTime(Long id) {
        return null;
    }

    @Override
    public WorkingHourDTO insertWorkTime(Long id, WorkingHourDTO workingHour) {
        return null;
    }

    @Override
    public AllRidesDTO getRides(Long id) {
        return null;
    }

    @Override
    public WorkingHourDTO getWorkTime(Long workTimeId, boolean flag) {
        return null;
    }

    @Override
    public WorkingHourDTO updateWorkTime(Long workTimeId) {
        return null;
    }
}
