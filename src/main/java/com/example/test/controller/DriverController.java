package com.example.test.controller;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IDriverService;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<Driver> insert(@RequestBody Driver driver) throws Exception{
        Driver returnedDriver = service.insert(driver);
        if (returnedDriver == null) {
            return new ResponseEntity<Driver>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Driver>(returnedDriver, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Driver>> getAll() throws Exception{
        Collection<Driver> drivers =  service.getAll();
        if (drivers == null) {
            return new ResponseEntity<Collection<Driver>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Driver>>(drivers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> get(@PathVariable Long id) throws Exception {
        Driver driver = service.get(id);
        // TODO : add 400 status
        if (driver == null) {
            return new ResponseEntity<Driver>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> update (@PathVariable Long id, @RequestBody Driver driver) throws Exception {
        Driver returnedDriver = service.update(id, driver);
        // TODO : add 400 status
        if (returnedDriver == null) {
            return new ResponseEntity<Driver>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Driver>(returnedDriver, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Document>> getDriverDocuments(@PathVariable Long id) throws Exception {
        Collection<Document> driverDocuments = service.getDriverDocuments(id);
        // TODO : add 400 status
        if (driverDocuments == null) {
            return new ResponseEntity<Collection<Document>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Document>>(driverDocuments, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/documents")
    public ResponseEntity<Boolean> deleteDriverDocument(@PathVariable Long id) throws Exception {
        Boolean isDeleted = service.deleteDriverDocument(id);
        // TODO : add 400 status
        if (isDeleted == null) {
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(isDeleted, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/{id}/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Document>> insertDriverDocuments(@PathVariable Long id,
                                                                      @RequestBody Collection<Document>
                                                                                         driverDocuments)
                                                                            throws Exception{
        Collection<Document> returnedDriverDocuments = service.insertDriverDocuments(id, driverDocuments);
        // TODO : add 400 status
        if (returnedDriverDocuments == null) {
            return new ResponseEntity<Collection<Document>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Document>>(returnedDriverDocuments, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) throws Exception {
        Vehicle vehicle = service.getVehicle(id);
        // TODO : add 400 status
        if (vehicle == null) {
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> insertVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) throws Exception {
        Vehicle returnedVehicle = service.insertVehicle(id, vehicle);
        // TODO : add 400 status
        if (returnedVehicle == null) {
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Vehicle>(returnedVehicle, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) throws Exception {
        Vehicle updatedVehicle = service.updateVehicle(id, vehicle);
        // TODO : add 400 status
        if (updatedVehicle == null) {
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Vehicle>(updatedVehicle, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/working-hours", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHour> getWorkTime(@PathVariable Long id) throws Exception{
        WorkingHour workingHour = service.getWorkTime(id);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHour>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHour>(workingHour, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/working-hours", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHour> insertWorkTime(@PathVariable Long id, @RequestBody WorkingHour workingHour)
                                                    throws Exception {
        WorkingHour updatedWorkingHour = service.insertWorkTime(id, workingHour);
        // TODO : add 400 status
        if (updatedWorkingHour == null) {
            return new ResponseEntity<WorkingHour>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHour>(workingHour, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ride>> getRides(@PathVariable Long id) throws Exception {
        Collection<Ride> rides = service.getRides(id);
        // TOD : add 400 status
        if (rides == null) {
            return new ResponseEntity<Collection<Ride>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Ride>>(rides, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/working-hour/{working-hour-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHour> getWorkTime(@PathVariable Long id, @PathVariable Long workTimeId) throws Exception {
        WorkingHour workingHour = service.getWorkTime(id, workTimeId);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHour>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHour>(workingHour, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/working-hour/{working-hour-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHour> updateWorkTime(@PathVariable Long id, @PathVariable Long workTimeId)
                                                    throws Exception {
        WorkingHour workingHour = service.updateWorkTime(id, workTimeId);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHour>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHour>(workingHour, HttpStatus.OK);
    }

}
