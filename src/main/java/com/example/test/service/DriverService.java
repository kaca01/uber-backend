package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.dto.AllDTO;
import com.example.test.dto.business.WorkingHourDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.DocumentDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.vehicle.VehicleDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.repository.business.IWorkingHourRepository;
import com.example.test.repository.ride.ILocationRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IDocumentRepository;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.repository.vehicle.IVehicleRepository;
import com.example.test.repository.vehicle.IVehicleTypeRepository;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class DriverService implements IDriverService {

    @Autowired
    IDriverRepository iDriverRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IDocumentRepository iDocumentRepository;
    @Autowired
    IVehicleRepository iVehicleRepository;
    @Autowired
    ILocationRepository iLocationRepository;
    @Autowired
    IRideRepository iRideRepository;
    @Autowired
    IWorkingHourRepository iWorkingHourRepository;
    @Autowired
    IVehicleTypeRepository iVehicleTypeRepository;

    @Override
    public UserDTO insert(UserDTO driverDTO) {
        Driver driver = new Driver(driverDTO);
        iDriverRepository.save(driver);
        driverDTO.setId(driver.getId());
        return driverDTO;
    }

    @Override
    public AllDTO<UserDTO> getAll() {
        List<Driver> drivers = iDriverRepository.findAll();
        List<UserDTO> driversDTO = new ArrayList<UserDTO>();
        for (Driver driver : drivers) driversDTO.add(new UserDTO(driver));
        return new AllDTO<>(driversDTO.size(), driversDTO);
    }

    @Override
    public UserDTO get(Long id) {
        Driver driver =  iDriverRepository.findById(id);
        return new UserDTO(driver);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO driverDTO) {
        driverDTO.setId(id);
        if (getDriver(id) == null) return null;
        Driver driver = new Driver(driverDTO);
        iUserRepository.save(driver);
        return driverDTO;
    }

    @Override
    public List<DocumentDTO> getDriverDocuments(Long id) {
        Driver driver = getDriver(id);
        List<Document> documents = iDocumentRepository.findDocumentsByDriverId(driver);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (Document document : documents) documentDTOS.add(new DocumentDTO(document));
        return documentDTOS;
    }

    @Override
    public DocumentDTO insertDriverDocument(Long id, DocumentDTO documentDTO) {
        Driver driver = getDriver(id);
        if (driver == null) return null;
        Document document = new Document(documentDTO.getName(), documentDTO.getDocumentImage(), null);
        document.setDriver(driver);
        iDocumentRepository.save(document);
        documentDTO = new DocumentDTO(document);
        return documentDTO;
    }

    @Override
    @Transactional
    public DocumentDTO deleteDriverDocument(Long id) {
        Document document = iDocumentRepository.findById(id);
        if (document == null) return null;
        iDocumentRepository.deleteDocumentById(id);
        DocumentDTO documentDTO = new DocumentDTO(document);
        return documentDTO;
    }

    @Override
    public VehicleDTO getVehicle(Long id) {
        Vehicle vehicle = iDriverRepository.findVehicleIdByDriverId(id);
        Driver driver = getDriver(id);
        return new VehicleDTO(driver, vehicle);
    }

    @Override
    public VehicleDTO insertVehicle(Long id, VehicleDTO vehicleDTO) {
        Driver driver = getDriver(id);
        if (driver == null) return null;
        vehicleDTO = saveLocation(vehicleDTO);
        String name = vehicleDTO.getVehicleType();
        VehicleType vehicleType = iVehicleTypeRepository.getByName(VehicleTypeName.valueOf(name));
        Vehicle vehicle = new Vehicle(vehicleDTO);
        vehicle.setType(vehicleType);
        iVehicleRepository.save(vehicle);
        driver.setVehicle(vehicle);
        iDriverRepository.save(driver);
        vehicleDTO.setId(vehicle.getId());
        return vehicleDTO;
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Driver driver = getDriver(id);
        if (driver == null) return null;
        vehicleDTO = saveLocation(vehicleDTO);
        Vehicle vehicle = new Vehicle(vehicleDTO);
        VehicleType type = iVehicleTypeRepository.getByName(VehicleTypeName.valueOf(vehicleDTO.getVehicleType()));
        vehicle.setType(type);
        driver.setVehicle(vehicle);
        iVehicleRepository.save(vehicle);
        iDriverRepository.save(driver);
        vehicleDTO.setId(vehicle.getId());
        return vehicleDTO;
    }

    @Override
    @Transactional
    public AllDTO<WorkingHourDTO> getWorkTimes(Long id) {
        Driver driver = getDriver(id);
        if (driver == null) return null;
        Set<WorkingHour> workingHours = driver.getWorkingHours();
        List<WorkingHourDTO> workingHourDTOS = new ArrayList<>();
        for (WorkingHour workingHour : workingHours) workingHourDTOS.add(new WorkingHourDTO(workingHour));
        return new AllDTO<WorkingHourDTO>(workingHours.size(), workingHourDTOS);
    }

    @Override
    @Transactional
    public WorkingHourDTO insertWorkTime(Long id, WorkingHourDTO workingHourDTO) throws ParseException {
        Driver driver = getDriver(id);
        if (driver == null) return null;
        Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getStart());
        Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getEnd());
        WorkingHour workingHour = new WorkingHour(workingHourDTO.getId(), start, end);
        driver.getWorkingHours().add(workingHour);
        // TODO : check if working hour is added
        iDriverRepository.save(driver);
        workingHourDTO.setId(workingHour.getId());
        return workingHourDTO;
    }

    @Override
    @Transactional
    public AllDTO<RideDTO> getRides(Long id) {
        Driver driver = getDriver(id);
        if (driver == null) return null;
        List<Ride> rides = iRideRepository.findRidesByDriverId(id);
        List<RideDTO> rideDTOS = new ArrayList<RideDTO>();
        for (Ride ride : rides)  rideDTOS.add(new RideDTO(ride));
        AllDTO<RideDTO> allRidesDTO = new AllDTO<>(rideDTOS.size(), rideDTOS);
        return allRidesDTO;
    }

    @Override
    public WorkingHourDTO getWorkTime(Long workTimeId) {
        WorkingHour workingHour = iWorkingHourRepository.findById(workTimeId);
        return new WorkingHourDTO(workingHour);
    }

    @Override
    public WorkingHourDTO updateWorkTime(Long workTimeId, WorkingHourDTO workingHourDTO) throws ParseException {
        if (iWorkingHourRepository.findById(workTimeId) == null) return null;
        workingHourDTO.setId(workTimeId);
        Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getStart());
        Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getEnd());
        WorkingHour workingHour = new WorkingHour(workTimeId, start, end);
        iWorkingHourRepository.save(workingHour);
        return workingHourDTO;
    }

    // TODO : move this to VehicleService
    public VehicleDTO saveLocation(VehicleDTO vehicle) {
        // TODO : create function for this in vehicle service after everyone finishes services
        // we are not here just saving location because there can be locations with the same longitude and
        // latitude but different address (address is a string, so it is tricky)
        // check if there is current location in database
        Location currentLocation = vehicle.getCurrentLocation();
        Location location = iLocationRepository.findByLatitudeAndLongitude(currentLocation.getLatitude(),
                currentLocation.getLongitude());
        // if there is not, save it
        if (location == null) iLocationRepository.save(currentLocation);
            // if there is set location from database
        else vehicle.setCurrentLocation(location);
        return vehicle;
    }

    private Driver getDriver(Long id) {
        return iDriverRepository.findById(id);
    }

    // TODO : driver is currently active
    // TODO : driver has compatible vehicle
    // TODO : driver has less than 8 working hours in the last 24 hours (with estimated time?)
    // TODO : driver does not have current ride
    // TODO : first case -> found one or more drivers that are currently active and don't have curr. ride -> DONE
    // TODO : second case -> there are active drivers but every driver has curr. ride -> find the one that does not
    // TODO : have the next ride -> if there is not... reject

    public List<Driver> findAvailable(Ride ride, String type) {
        // here are also limited drivers with incompatibility of vehicle
        List<Driver> activeDrivers = getActiveDrivers(type);
        if (activeDrivers.size() == 0) return null;
        List<Driver> availableDrivers = getAvailableDrivers(activeDrivers, ride);
        // if there are active available drivers, return them
        if (availableDrivers.size() > 0) return availableDrivers;
        // if there are no active available drivers, find drivers that do not have scheduled ride
        List<Driver> noScheduledRide = getDriversWithNoScheduledRide(activeDrivers, ride);
        if (noScheduledRide.size() == 0) return null;
        return removeFinishedForToday(noScheduledRide);
    }

    private List<Driver> getActiveDrivers(String type) {
        List<Driver> allDrivers = iDriverRepository.findAll();
        List<Driver> drivers = new ArrayList<>();
        for (Driver one: allDrivers) {
            if (!checkVehicleCompatibility(type, one)) continue;
            if (one.isActive()) drivers.add(one);
        }
        return drivers;
    }

    // returns active drivers with no current ride
    private List<Driver> getAvailableDrivers(List<Driver> activeDrivers, Ride newRide) {
        List<Driver> noScheduled = getDriversWithNoScheduledRide(activeDrivers, newRide);
        List<Driver> drivers = new ArrayList<>();
        for (Driver driver : activeDrivers) {
            Ride ride = iRideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, driver.getId());
            if (ride == null) {
                if (noScheduled.contains(driver)) drivers.add(driver);
            }
        }
        return drivers;
    }

    private List<Driver> getDriversWithNoScheduledRide(List<Driver> activeDrivers, Ride newRide) {
        List<Driver> drivers = new ArrayList<>();
        for (Driver driver : activeDrivers) {
            Ride ride = iRideRepository.findByStatusAndDriver_id(RideStatus.ACCEPTED, driver.getId());
            if (ride == null) {
                drivers.add(driver);
                continue;
            }
            Date rideShouldEnd = addMinutesToDate(ride.getStartTime(), (long) ride.getEstimatedTimeInMinutes());
            Date newRideShouldEnd = addMinutesToDate(newRide.getStartTime(), (long) newRide.getEstimatedTimeInMinutes());
            if (!checkIfRidesOverlap(ride.getStartTime(), rideShouldEnd, newRide.getStartTime(), newRideShouldEnd))
                drivers.add(driver);
        }
        return drivers;
    }

    private boolean checkIfRidesOverlap(Date firstRideStart, Date firstRideEnd, Date secondRideStart,
                                        Date secondRideEnd) {
        if (firstRideStart.before(secondRideStart) && firstRideEnd.before(secondRideStart)) return false;
        else if (firstRideStart.after(secondRideEnd) && firstRideEnd.after(secondRideEnd)) return false;
        return true;
    }


    private Date addMinutesToDate(Date date, long minutes) {
        long start = date.getTime();
        return new Date(start + (minutes * 60000));
    }

    private List<Driver> removeFinishedForToday(List<Driver> drivers) {
        drivers.removeIf(driver -> isFinishedForToday(driver.getWorkingHours()));
        return drivers;
    }

    // checks if driver has more than 8 hours in the last 24 hours
    private boolean isFinishedForToday(Set<WorkingHour> workingHours) {
        // this should be called from the function that chooses one driver
        double sumOfHours = 0;
        Date date = find24HoursAgo();
        List<WorkingHour> todayWorkingHours = new ArrayList<>();
        for (WorkingHour workingHour : workingHours) {
            Date workingHourStarts = workingHour.getStart();
            Date workingHourEnds = workingHour.getEnd();
            if (workingHourStarts.after(date)) {
                sumOfHours += calculateHoursDifference(workingHourStarts, workingHourEnds);
            } else if (workingHourEnds.after(date)) {
                sumOfHours += calculateHoursDifference(date, workingHourEnds);
            }
        }
        return sumOfHours >= 8;
    }

    private long calculateHoursDifference(Date startDate, Date endDate) {
        long differenceInTime = endDate.getTime() - startDate.getTime();

        return (differenceInTime / (1000 * 60 * 60)) % 24;
    }

    private Date find24HoursAgo() {
        Instant instant = Instant.now().minus(24, ChronoUnit.HOURS);
        return Date.from(instant);
    }

    private boolean checkVehicleCompatibility(String vehicleType, Driver driver) {
        return (driver.getVehicle().getType().getName().toString().equals(vehicleType));
    }

}