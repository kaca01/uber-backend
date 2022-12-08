package com.example.test.controller;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.*;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO driver) throws Exception{
        UserDTO returnedDriver = service.insert(driver);
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllUsersDTO> getAll() throws Exception{
        AllUsersDTO drivers =  service.getAll();
        if (drivers == null) {
            return new ResponseEntity<AllUsersDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AllUsersDTO>(drivers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable Long id) throws Exception {
        UserDTO driver = service.get(id);
        // TODO : add 400 status
        if (driver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(driver, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update (@PathVariable Long id, @RequestBody UserDTO driver) throws Exception {
        UserDTO returnedDriver = service.update(id, driver);
        // TODO : add 400 status
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    // TODO discuss this with team
    public ResponseEntity<Collection<DocumentDTO>> getDriverDocuments(@PathVariable Long id) throws Exception {
        Collection<DocumentDTO> driverDocuments = service.getDriverDocuments(id);
        // TODO : add 400 status
        if (driverDocuments == null) {
            return new ResponseEntity<Collection<DocumentDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<DocumentDTO>>(driverDocuments, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDTO> insertDriverDocuments(@PathVariable Long id,
                                                                      @RequestBody DocumentDTO document)
            throws Exception{
        DocumentDTO returnedDriverDocuments = service.insertDriverDocument(id, document);
        // TODO : add 400 status
        if (returnedDriverDocuments == null) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DocumentDTO>(returnedDriverDocuments, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/document/{document-id}")
    public ResponseEntity<Boolean> deleteDriverDocument(@PathVariable Long id) throws Exception {
        Boolean isDeleted = service.deleteDriverDocument(id);
        // TODO : add 400 status
        if (isDeleted == null) {
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        } else if (isDeleted == Boolean.FALSE) {
            return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(isDeleted, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) throws Exception {
        VehicleDTO vehicle = service.getVehicle(id);
        // TODO : add 400 status
        if (vehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(vehicle, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> insertVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicle)
            throws Exception {
        VehicleDTO returnedVehicle = service.insertVehicle(id, vehicle);
        // TODO : add 400 status
        if (returnedVehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(returnedVehicle, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicle)
            throws Exception {
        VehicleDTO updatedVehicle = service.updateVehicle(id, vehicle);
        // TODO : add 400 status
        if (updatedVehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(updatedVehicle, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllWorkingHoursDTO> getWorkTimes(@PathVariable Long id) throws Exception{
        AllWorkingHoursDTO workingHour = service.getWorkTime(id);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<AllWorkingHoursDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AllWorkingHoursDTO>(workingHour, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> insertWorkTime(@PathVariable Long id, @RequestBody WorkingHourDTO workingHour)
                                                    throws Exception {
        WorkingHourDTO updatedWorkingHour = service.insertWorkTime(id, workingHour);
        // TODO : add 400 status
        if (updatedWorkingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(updatedWorkingHour, HttpStatus.CREATED);
    }

    // TODO is here missing AllRidesDTO?
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ride>> getRides(@PathVariable Long id) throws Exception {
        Collection<Ride> rides = service.getRides(id);
        // TOD : add 400 status
        if (rides == null) {
            return new ResponseEntity<Collection<Ride>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Ride>>(rides, HttpStatus.OK);
    }

    @GetMapping(value = "/working-hour/{working-hour-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> getWorkTime(@PathVariable Long workTimeId) throws Exception {
        WorkingHourDTO workingHour = service.getWorkTime(workTimeId, true);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }

    @PutMapping(value = "/working-hour/{working-hour-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> updateWorkTime(@PathVariable Long workTimeId) throws Exception {
        WorkingHourDTO workingHour = service.updateWorkTime(workTimeId);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }

}
