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
        // latitude but different address (address is a string so it is tricky)
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