package com.example.test.controller;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Document;
import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.dto.*;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IDriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    private ModelMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO driverDTO) throws Exception{
        Driver driver = new Driver(driverDTO);
        Driver returnedDriver = service.insert(driver);
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(driverDTO, HttpStatus.BAD_REQUEST);
        }
        driverDTO = new UserDTO(returnedDriver);

        return new ResponseEntity<UserDTO>(driverDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllUsersDTO> getAll() throws Exception{
        List<Driver> drivers =  service.getAll();
        List<UserDTO> driversDTO = new ArrayList<UserDTO>();
        for (Driver driver : drivers) driversDTO.add(new UserDTO(driver));
        AllUsersDTO allUsersDTO = new AllUsersDTO(driversDTO.size(), driversDTO);
        if (drivers == null) {
            return new ResponseEntity<AllUsersDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AllUsersDTO>(allUsersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable Long id) throws Exception {
        Driver driver = service.get(id);
        if (driver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        UserDTO driverDTO = new UserDTO(driver);
        // TODO : add 400 status

        return new ResponseEntity<UserDTO>(driverDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update (@PathVariable Long id, @RequestBody UserDTO driverDTO) throws Exception {
        Driver driver = new Driver(driverDTO);
        Driver returnedDriver = service.update(id, driver);
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        driverDTO = new UserDTO(returnedDriver);
        // TODO : add 400 status

        return new ResponseEntity<UserDTO>(driverDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDTO>> getDriverDocuments(@PathVariable Long id) throws Exception {
        List<Document> driverDocuments = service.getDriverDocuments(id);
        // TODO : add 400 status
        if (driverDocuments == null) {
            return new ResponseEntity<List<DocumentDTO>>(HttpStatus.NOT_FOUND);
        }
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (Document document : driverDocuments)  documentDTOS.add(new DocumentDTO(document));
        return new ResponseEntity<List<DocumentDTO>>(documentDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDTO> insertDriverDocuments(@PathVariable Long id,
                                                             @RequestBody DocumentDTO documentDTO)
            throws Exception{
        Document document = new Document(documentDTO.getName(), documentDTO.getDocumentImage(), null);
        Document returnedDriverDocument = service.insertDriverDocument(id, document);
        // TODO : add 400 status
        if (returnedDriverDocument == null) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
        }
        documentDTO = new DocumentDTO(returnedDriverDocument);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/document/{document-id}")
    public ResponseEntity<DocumentDTO> deleteDriverDocument(@PathVariable Long id) throws Exception {
        Document document = service.deleteDriverDocument(id);
        // TODO : add 400 status
        if (document == null) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
        }
        DocumentDTO documentDTO = new DocumentDTO(document);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) throws Exception {
        Vehicle vehicle = service.getVehicle(id);
        Driver driver = service.get(id);
        VehicleDTO vehicleDTO = new VehicleDTO(driver, vehicle);
        // TODO : add 400 status
        if (vehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(vehicleDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> insertVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO)
            throws Exception {
        Vehicle vehicle = new Vehicle(vehicleDTO.getId(), new VehicleType(1L, VehicleTypeName.STANDARD, 50), vehicleDTO.getModel(),
                vehicleDTO.getLicenseNumber(), vehicleDTO.getPassengerSeats(), vehicleDTO.getCurrentLocation(),
                vehicleDTO.getBabyTransport(), vehicleDTO.getPetTransport());
        Vehicle returnedVehicle = service.insertVehicle(id, vehicle);
        // TODO : add 400 status
        if (returnedVehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        Driver driver = service.get(id);
        vehicleDTO = new VehicleDTO(driver, vehicle);
        return new ResponseEntity<VehicleDTO>(vehicleDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO)
            throws Exception {
        Vehicle vehicle = new Vehicle(vehicleDTO.getId(), new VehicleType(1L, VehicleTypeName.STANDARD, 50), vehicleDTO.getModel(),
                vehicleDTO.getLicenseNumber(), vehicleDTO.getPassengerSeats(), vehicleDTO.getCurrentLocation(),
                vehicleDTO.getBabyTransport(), vehicleDTO.getPetTransport());
        Vehicle updatedVehicle = service.updateVehicle(id, vehicle);
        Driver driver = service.get(id);
        vehicleDTO = new VehicleDTO(driver, updatedVehicle);
        // TODO : add 400 status
        if (updatedVehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(vehicleDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllWorkingHoursDTO> getWorkTimes(@PathVariable Long id) throws Exception{
        List<WorkingHour> workingHours = service.getWorkTime(id);
        // TODO : add 400 status
        if (workingHours == null) {
            return new ResponseEntity<AllWorkingHoursDTO>(HttpStatus.NOT_FOUND);
        }
        ArrayList<WorkingHourDTO> workingHourDTOS = new ArrayList<>();
        for (WorkingHour workingHour : workingHours) workingHourDTOS.add(new WorkingHourDTO(workingHour));
        AllWorkingHoursDTO allWorkingHoursDTO = new AllWorkingHoursDTO(workingHours.size(), workingHourDTOS);

        return new ResponseEntity<AllWorkingHoursDTO>(allWorkingHoursDTO, HttpStatus.OK);
    }

    /*@PostMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> insertWorkTime(@PathVariable Long id,
                                                         @RequestBody WorkingHourDTO workingHourDTO) throws Exception {
        Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(workingHourDTO.getStart());
        Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(workingHourDTO.getEnd());
        WorkingHour workingHour = new WorkingHour(workingHourDTO.getId(), start, end);
        WorkingHour updatedWorkingHour = service.insertWorkTime(id, workingHour);
        // TODO : add 400 status
        if (updatedWorkingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        workingHourDTO = new WorkingHourDTO(updatedWorkingHour);

        return new ResponseEntity<WorkingHourDTO>(workingHourDTO, HttpStatus.OK);
    }*/

    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllRidesDTO> getRides(@PathVariable Long id) throws Exception {
        List<Ride> rides = service.getRides(id);
        // TODO : add 400 status
        if (rides == null) {
            return new ResponseEntity<AllRidesDTO>(HttpStatus.NOT_FOUND);
        }
        ArrayList<RideDTO> rideDTOS = new ArrayList<RideDTO>();
        for (Ride ride : rides)  rideDTOS.add(new RideDTO(ride));
        AllRidesDTO allRidesDTO = new AllRidesDTO(rideDTOS.size(), rideDTOS);

        return new ResponseEntity<AllRidesDTO>(allRidesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/working-hour/{working-hour-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> getWorkTime(@PathVariable Long workTimeId) throws Exception {
        WorkingHour workingHour = service.getWorkTime(workTimeId, true);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        WorkingHourDTO workingHourDTO = new WorkingHourDTO(workingHour);

        return new ResponseEntity<WorkingHourDTO>(workingHourDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/working-hour/{working-hour-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> updateWorkTime(@PathVariable Long workTimeId) throws Exception {
        WorkingHour workingHour = service.updateWorkTime(workTimeId);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        WorkingHourDTO workingHourDTO = new WorkingHourDTO(workingHour);

        return new ResponseEntity<WorkingHourDTO>(workingHourDTO, HttpStatus.OK);
    }

}
