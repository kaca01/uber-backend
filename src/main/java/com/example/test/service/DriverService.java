package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DriverService implements IDriverService {

    private ArrayList<Driver> drivers;
    private ArrayList<Document> documents;
    private ArrayList<WorkingHour> workTimes;
    private ArrayList<Ride> rides;

    {
        try {
            drivers = createDrivers();
            documents = createDocuments();
            workTimes = createWorkTimes();
            rides = createRides();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Driver insert(Driver driver) {
        driver.setId(123L);  // TODO implement this
        drivers.add(driver);
        return driver;
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
        if (get(id) == null) return null;
        List<Document> driversDocuments = new ArrayList<>();
        for (Document document : documents) {
            if (document.getDriver().getId().equals(id)) driversDocuments.add(document);
        }
        return driversDocuments;
    }

    @Override
    public Document insertDriverDocument(Long id, Document document) {
        Driver driver = get(id);
        if (driver == null) return null;
        document.setDriver(driver);
        document.setId(123L);  // TODO implement this
        documents.add(document);
        return document;
    }

    @Override
    public Document deleteDriverDocument(Long id) {
        for (Document document : documents) {
            if (document.getId().equals(id)) {
                documents.remove(document);
                return document;
            }
        }
        return null;
    }

    @Override
    public Vehicle getVehicle(Long id) {
        Driver driver = get(id);
        return driver.getVehicle();
    }

    @Override
    public Vehicle insertVehicle(Long id, Vehicle vehicle) {
        // TODO new id is always the same (this will be implemented after database is added)
        Driver driver = get(id);
        if (driver == null) return null;
        vehicle.setId(123L);
        driver.setVehicle(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Driver driver = get(id);
        if (driver == null) return null;
        Vehicle oldVehicle = driver.getVehicle();
        oldVehicle.setType(vehicle.getType());
        oldVehicle.setModel(vehicle.getModel());
        oldVehicle.setLicenseNumber(vehicle.getLicenseNumber());
        oldVehicle.setPassengerSeats(vehicle.getPassengerSeats());
        oldVehicle.setCurrentLocation(vehicle.getCurrentLocation());
        oldVehicle.setBabyTransport(vehicle.isBabyTransport());
        oldVehicle.setPetTransport(vehicle.isPetTransport());

        return oldVehicle;
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
        Driver d = get(id);
        if (d == null) return null;
        List<Ride> foundRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getDriver().getId().equals(id)) foundRides.add(ride);
        }
        return foundRides;
    }

    @Override
    public WorkingHour getWorkTime(Long workTimeId) {
        for (WorkingHour workTime : workTimes) {
            if (workTime.getId().equals(workTimeId)) return workTime;
        }
        return null;
    }

    @Override
    public WorkingHour updateWorkTime(Long workTimeId, WorkingHour workingHour) {
        for (WorkingHour workTime : workTimes) {
            if (workTime.getId().equals(workTimeId)) {
                workTime.setStart(workingHour.getStart());
                workTime.setEnd(workingHour.getEnd());
                return workTime;
            }
        }
        return null;
    }

    private ArrayList<Driver> createDrivers() throws ParseException {
        VehicleType type = new VehicleType(1L, VehicleTypeName.STANDARD, 50);
        Location location = new Location(1L, "adresa", 544, 546 );
        Vehicle v1 = new Vehicle(1L, type, "model", "NS-123-45", 4, location, true, false);
        Set<WorkingHour> workingHours = new HashSet<>();

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

    private ArrayList<Document> createDocuments() {
        ArrayList<Document> documents = new ArrayList<>();
        assert drivers != null;
        assert drivers.size() > 3;
        Document d1 = new Document(1L, "vozacka", "U3dhZ2dlciByb2Nrcw==", drivers.get(0));
        Document d2 = new Document(2L, "vozacka", "U3dhZ2dlciByb2Nrcw==", drivers.get(1));
        Document d3 = new Document(3L, "vozacka", "U3dhZ2dlciByb2Nrcw==", drivers.get(2));
        Document d4 = new Document(4L, "vozacka", "U3dhZ2dlciByb2Nrcw==", drivers.get(3));
        Document d5 = new Document(5L, "licna", "U3dhZ2dlciByb2Nrcw==", drivers.get(0));
        documents.add(d1);
        documents.add(d2);
        documents.add(d3);
        documents.add(d4);
        documents.add(d5);
        return documents;
    }

    private ArrayList<WorkingHour> createWorkTimes() throws ParseException{
        ArrayList<WorkingHour> workingHours = new ArrayList<WorkingHour>();

        WorkingHour w1 = new WorkingHour(1L,
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2022-12-10T20:55:16.868Z"),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2022-12-10T23:55:16.868Z"));
        WorkingHour w2 = new WorkingHour(2L,
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2022-12-08T20:55:16.868Z"),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2022-12-08T23:55:16.868Z"));
        WorkingHour w3 = new WorkingHour(3L,
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2021-12-08T20:55:16.868Z"),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2021-12-08T23:55:16.868Z"));
        WorkingHour w4 = new WorkingHour(10L,
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-12-08T20:55:16.868Z"),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2020-12-08T23:55:16.868Z"));
        workingHours.add(w1);
        workingHours.add(w2);
        workingHours.add(w3);
        workingHours.add(w4);
        return workingHours;
    }

    private ArrayList<Ride> createRides() throws ParseException{
        ArrayList<Ride> rides = new ArrayList<>();
        /*Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2021-12-08T20:55:16.868Z");
        Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2021-12-08T22:55:16.868Z");
        Passenger passenger = new Passenger(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com",
                "Nikole Pasica 25", "sifra123", false, true, null);
        ArrayList<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        Location location = new Location(1L, 544, 546, "adresa");
        VehicleType type = new VehicleType(1L, VehicleTypeName.STANDARD, 50);
        Vehicle v = new Vehicle(1L, type, "model", "NS-123-45", 4, location, true, false);
        Rejection rejection = new Rejection(1L, "", passenger, start);
        Ride r1 = new Ride(1L, start, end, 22.555, 15.748, v, drivers.get(0), passengers, null,
                RideStatus.ACTIVE, rejection, true, true, null);
        rides.add(r1);*/

        return rides;
    }
}