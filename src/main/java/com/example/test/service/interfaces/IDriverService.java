package com.example.test.service.interfaces;

import com.example.test.domain.business.WorkTime;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.DriverDocument;
import com.example.test.domain.vehicle.Vehicle;
import org.hibernate.jdbc.Work;

import java.util.Collection;

public interface IDriverService {

    public Driver insert(Driver driver);

    // TODO : check this later (paginated drivers???)
    public Collection<Driver> getAll();

    public Driver get(Long id);

    public Driver update(Driver driver);

    public Collection<DriverDocument> getDriverDocuments(Long id);

    public boolean deleteDriverDocument(Long id);

    public Collection<DriverDocument> insertDriverDocuments(Long id, Collection<DriverDocument> driverDocument);

    public Vehicle getVehicle(Long id);

    public Vehicle insertVehicle(Long id, Vehicle vehicle);

    public Vehicle updateVehicle(Long id, Long vehicleId);

    public WorkTime getWorkTime(Long id);

    public WorkTime insertWorkTime(Long id, WorkTime workTime);

    public Collection<Ride> getRides(Long id);

    public WorkTime getWorkTime(Long id, Long workTimeId);

    public WorkTime updateWorkTime(Long id, WorkTime workTime);

}
