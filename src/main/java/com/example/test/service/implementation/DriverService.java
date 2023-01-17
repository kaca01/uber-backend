package com.example.test.service.implementation;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.user.User;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.dto.AllDTO;
import com.example.test.dto.business.WorkingHourDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.DocumentDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.vehicle.VehicleDTO;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.business.IWorkingHourRepository;
import com.example.test.repository.ride.ILocationRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IDocumentRepository;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.repository.vehicle.IVehicleRepository;
import com.example.test.repository.vehicle.IVehicleTypeRepository;
import com.example.test.service.interfaces.IDriverService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Optional<User> user = iUserRepository.findByEmail(driverDTO.getEmail().trim());
        if (user.isPresent()) throw new BadRequestException("User with that email already exists!");
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
        if (driver == null) throw new NotFoundException("Driver does not exist!");
        return new UserDTO(driver);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO driverDTO) {
        driverDTO.setId(id);
        if (getDriver(id) == null) throw new NotFoundException("Driver does not exist!");
        Driver driver = new Driver(driverDTO);
        iUserRepository.save(driver);
        return driverDTO;
    }

    @Override
    public List<DocumentDTO> getDriverDocuments(Long id) {
        Driver driver = getDriver(id);
        if (driver == null) throw new NotFoundException("Driver does not exist!");
        List<Document> documents = iDocumentRepository.findDocumentsByDriverId(driver);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (Document document : documents) documentDTOS.add(new DocumentDTO(document));
        return documentDTOS;
    }

    @Override
    public DocumentDTO insertDriverDocument(Long id, DocumentDTO documentDTO) {
        Driver driver = getDriver(id);
        if (driver == null) throw new NotFoundException("Driver does not exist!");
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
        if (document == null) throw new NotFoundException("Document does not exist!");
        iDocumentRepository.deleteDocumentById(id);
        return new DocumentDTO(document);
    }

    @Override
    public VehicleDTO getVehicle(Long id) {
        Driver driver = getDriver(id);
        if (driver == null) throw new NotFoundException("Driver does not exist!");
        Vehicle vehicle = iDriverRepository.findVehicleIdByDriverId(id);
        if (vehicle == null) throw new BadRequestException("Vehicle is not assigned!");
        return new VehicleDTO(driver, vehicle);
    }

    @Override
    public VehicleDTO insertVehicle(Long id, VehicleDTO vehicleDTO) {
        Driver driver = getDriver(id);
        if (driver == null) throw new NotFoundException("Driver does not exist!");
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
        if (driver == null) throw new NotFoundException("Driver does not exist!");
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
        if (driver == null) throw new NotFoundException("Driver does not exist!");
        Set<WorkingHour> workingHours = driver.getWorkingHours();
        List<WorkingHourDTO> workingHourDTOS = new ArrayList<>();
        for (WorkingHour workingHour : workingHours) workingHourDTOS.add(new WorkingHourDTO(workingHour));
        return new AllDTO<WorkingHourDTO>(workingHours.size(), workingHourDTOS);
    }

    @Override
    @Transactional
    public WorkingHourDTO insertWorkTime(Long id, WorkingHourDTO workingHourDTO) throws ParseException {
        Driver driver = getDriver(id);
        if (driver == null) throw new NotFoundException("Driver does not exist!");
        Set<WorkingHour> workingHours = driver.getWorkingHours();
        SelectionDriver selectionDriver = new SelectionDriver();
        if (selectionDriver.isFinishedForToday(workingHours))
            throw new BadRequestException("Cannot start shift because you exceeded the 8 hours limit in last 24 hours!");
        if (driver.getVehicle() == null)
            throw new BadRequestException("Cannot start shift because the vehicle is not defined!");
        for (WorkingHour workingHour : workingHours) if (workingHour.getEnd() == null)
            throw new BadRequestException("Shifth already ongoing!");
        Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getStart());
        WorkingHour workingHour = new WorkingHour();
        workingHour.setStart(start);
        workingHour = iWorkingHourRepository.save(workingHour);
        // TODO : check if working hour is added
        workingHours.add(workingHour);
        iDriverRepository.save(driver);
        workingHourDTO.setId(workingHour.getId());
        return workingHourDTO;
    }

    @Override
    @Transactional
    public AllDTO<RideDTO> getRides(Long id) {
        Driver driver = getDriver(id);
        if (driver == null) throw new NotFoundException("Driver does not exist!");
        List<Ride> rides = iRideRepository.findRidesByDriverId(id);
        List<RideDTO> rideDTOS = new ArrayList<RideDTO>();
        for (Ride ride : rides)  rideDTOS.add(new RideDTO(ride));
        return new AllDTO<>(rideDTOS.size(), rideDTOS);
    }

    @Override
    public WorkingHourDTO getWorkTime(Long workTimeId) {
        WorkingHour workingHour = iWorkingHourRepository.findById(workTimeId);
        if (workingHour == null) throw new NotFoundException("Working hour does not exist!");
        return new WorkingHourDTO(workingHour);
    }

    @Override
    public WorkingHourDTO updateWorkTime(Long workTimeId, WorkingHourDTO workingHourDTO) throws ParseException {
        if (iWorkingHourRepository.findById(workTimeId) == null)
            throw new NotFoundException("Working hour does not exist!");
        // get driver
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Driver driver = iDriverRepository.findByEmail(email);
        if (driver.getVehicle() == null) throw new BadRequestException("Cannot end shift because the vehicle is not defined!");
        Set<WorkingHour> workingHours = driver.getWorkingHours();
        WorkingHour onGoingWorkingHour = null;
        for (WorkingHour workingHour : workingHours) {
            if (workingHour.getEnd() == null) {
                onGoingWorkingHour = workingHour;
                break;
            }
        }
        if (onGoingWorkingHour == null) throw new BadRequestException("No shift is ongoing");
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
}