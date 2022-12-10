package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DriverService implements IDriverService {

    private ArrayList<Driver> drivers;

    {
        try {
            drivers = createDrivers();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Driver insert(Driver driver) {
        return null;
    }

    @Override
    public List<Driver> getAll() {
        return drivers;
    }

    @Override
    public Driver get(Long id) {
        for (Driver driver : drivers) {
            if (driver.getId().equals(id)) return driver;
        }
        return null;
    }

    @Override
    public Driver update(Long id, Driver driver) {
        Driver oldDriver = get(id);
        if (oldDriver == null) return null;
        oldDriver.setName(driver.getName());
        oldDriver.setSurname(driver.getSurname());
        oldDriver.setProfilePicture(driver.getProfilePicture());
        oldDriver.setTelephoneNumber(driver.getTelephoneNumber());
        oldDriver.setEmail(driver.getEmail());
        oldDriver.setAddress(driver.getAddress());
        oldDriver.setPassword(driver.getPassword());

        return oldDriver;
    }

    @Override
    public List<Document> getDriverDocuments(Long id) {
        // TODO implement this
        return null;
    }

    @Override
    public Boolean deleteDriverDocument(Long id) {
        // TODO implement this
        return false;
    }

    @Override
    public Document insertDriverDocument(Long id, Document document) {
        // TODO implement this
        return null;
    }

    @Override
    public Vehicle getVehicle(Long id) {
        Driver driver = get(id);
        return driver.getVehicle();
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

    public ArrayList<Driver> createDrivers() throws ParseException {
        VehicleType type = new VehicleType(1L, VehicleTypeName.STANDARD, 50);
        Location location = new Location(1L, "adresa", 544, 546);
        Vehicle v1 = new Vehicle(1L, type, "model", "NS-123-45", 4, location, true, false);
        WorkingHour workingHour = new WorkingHour(1L,
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2022-12-10T20:55:16.868Z"),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2022-12-10T23:55:16.868Z"));
        ArrayList<WorkingHour> workingHours = new ArrayList<WorkingHour>();
        workingHours.add(workingHour);

        Driver d1 = new Driver(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com",
                "Nikole Pasica 25", "sifra123", false, true, 1245678, workingHours, v1);
        Driver d2 = new Driver(2L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com",
                "Nikole Pasica 25", "sifra123", false, true, 1245678, workingHours, v1);
        Driver d3 = new Driver(3L, "Neko", "Nekic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com",
                "Nikole Pasica 25", "sifra123", false, true, 1245678, workingHours, v1);
        Driver d4 = new Driver(4L, "Bosko", "Radojcic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com",
                "Nikole Pasica 25", "sifra123", false, true, 1245678, workingHours, v1);
        ArrayList<Driver> drivers = new ArrayList<>();
        drivers.add(d1);
        drivers.add(d2);
        drivers.add(d3);
        drivers.add(d4);
        return drivers;
    }
}