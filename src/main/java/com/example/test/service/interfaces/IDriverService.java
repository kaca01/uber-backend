package com.example.test.service.interfaces;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.DocumentDTO;

import java.util.Collection;
import java.util.List;

public interface IDriverService {

    public Driver insert(Driver driver);

    // TODO : check this later (paginated drivers???)
    public List<Driver> getAll();

    public Driver get(Long id);

    public Driver update(Long id, Driver driver);

    public List<Document> getDriverDocuments(Long id);

    public Document insertDriverDocument(Long id, Document driverDocument);

    public Document deleteDriverDocument(Long id);

    public Vehicle getVehicle(Long id);

    public Vehicle insertVehicle(Long id, Vehicle vehicle);

    public Vehicle updateVehicle(Long id, Vehicle vehicle);

    public List<WorkingHour> getWorkTime(Long id);

    public WorkingHour insertWorkTime(Long id, WorkingHour workingHour);

    public List<Ride> getRides(Long id);

    public WorkingHour getWorkTime(Long workTimeId, boolean flag);

    public WorkingHour updateWorkTime(Long workTimeId, WorkingHour workingHour);
}