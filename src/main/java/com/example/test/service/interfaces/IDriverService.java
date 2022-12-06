package com.example.test.service.interfaces;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.DriverDocument;
import com.example.test.domain.vehicle.Vehicle;

import java.util.Collection;

public interface IDriverService {

    public Driver insert(Driver driver);

    // TODO : check this later (paginated drivers???)
    public Collection<Driver> getAll();

    public Driver get(Long id);

    public Driver update(Long id, Driver driver);

    public Collection<DriverDocument> getDriverDocuments(Long id);

    public Boolean deleteDriverDocument(Long id);

    public Collection<DriverDocument> insertDriverDocuments(Long id, Collection<DriverDocument> driverDocument);

    public Vehicle getVehicle(Long id);

    public Vehicle insertVehicle(Long id, Vehicle vehicle);

    public Vehicle updateVehicle(Long id, Vehicle vehicle);

    public WorkingHour getWorkTime(Long id);

    public WorkingHour insertWorkTime(Long id, WorkingHour workingHour);

    public Collection<Ride> getRides(Long id);

    public WorkingHour getWorkTime(Long id, Long workTimeId);

    public WorkingHour updateWorkTime(Long id, Long workTimeId);

}
