package com.example.test.controller;

import com.example.test.dto.AllDTO;
import com.example.test.dto.business.WorkingHourDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.DocumentDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.vehicle.VehicleDTO;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    // add new driver
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO driverDTO) throws Exception{
        UserDTO returnedDriver = service.insert(driverDTO);
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    // get all drivers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllDTO<UserDTO>> getAll() throws Exception{
        AllDTO<UserDTO> drivers = service.getAll();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    // get driver by id
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable Long id) throws Exception {
        UserDTO driver = service.get(id);
        return new ResponseEntity<UserDTO>(driver, HttpStatus.OK);
    }

    // update existing driver
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update (@PathVariable Long id, @Valid @RequestBody UserDTO driverDTO) throws Exception {
        UserDTO returnedDriver = service.update(id, driverDTO);
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    // get documents for driver
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDTO>> getDriverDocuments(@PathVariable Long id) throws Exception {
        List<DocumentDTO> driverDocuments = service.getDriverDocuments(id);
        return new ResponseEntity<List<DocumentDTO>>(driverDocuments, HttpStatus.OK);
    }

    // add documents for driver
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @PostMapping(value = "/{id}/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDTO> insertDriverDocuments(@PathVariable Long id,
                                                             @Valid @RequestBody DocumentDTO documentDTO)
            throws Exception{
        DocumentDTO returnedDriverDocument = service.insertDriverDocument(id, documentDTO);
        return new ResponseEntity<DocumentDTO>(returnedDriverDocument, HttpStatus.OK);
    }

    // delete documents for driver
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @DeleteMapping(value = "/document/{id}")
    public ResponseEntity<Void> deleteDriverDocument(@PathVariable Long id) throws Exception {
        DocumentDTO document = service.deleteDriverDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // get vehicle for a driver
    @GetMapping(value = "/{id}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) throws Exception {
        VehicleDTO vehicle = service.getVehicle(id);
        return new ResponseEntity<VehicleDTO>(vehicle, HttpStatus.OK);
    }

    // add new vehicle for driver
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> insertVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO)
            throws Exception {
        VehicleDTO returnedVehicle = service.insertVehicle(id, vehicleDTO);
        return new ResponseEntity<VehicleDTO>(returnedVehicle, HttpStatus.OK);
    }

    // update vehicle
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @PutMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO)
            throws Exception {
        VehicleDTO updatedVehicle = service.updateVehicle(id, vehicleDTO);
        return new ResponseEntity<VehicleDTO>(updatedVehicle, HttpStatus.OK);
    }

    //returns history of the driver working hours that can be filtered by data
    //id of the driver
    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<WorkingHourDTO>> getWorkTimes(@PathVariable Long id) throws Exception{
        AllDTO<WorkingHourDTO> workingHours = service.getWorkTimes(id);
        return new ResponseEntity<>(workingHours, HttpStatus.OK);
    }

    // creating information about the drivers working hours
    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> insertWorkTime(@PathVariable Long id,
                                                         @Valid @RequestBody WorkingHourDTO workingHourDTO) throws Exception {
        WorkingHourDTO updatedWorkingHour = service.insertWorkTime(id, workingHourDTO);
        return new ResponseEntity<WorkingHourDTO>(updatedWorkingHour, HttpStatus.OK);
    }

    // get rides for driver
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRides(@PathVariable Long id) throws Exception {
        AllDTO<RideDTO> rides = service.getRides(id);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //details about the working hour of the driver
    //id of working hour
    @PreAuthorize("hasRole('DRIVER')")
    // TODO : if added new role, service needs to be changed
    @GetMapping(value = "/working-hour/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> getWorkTime(@PathVariable Long id) throws Exception {
        WorkingHourDTO workingHour = service.getWorkTime(id);
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }

    //changing working hours
    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping(value = "/working-hour/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> updateWorkTime(@PathVariable Long id,
                                                         @Valid @RequestBody WorkingHourDTO workingHourDTO) throws Exception {
        WorkingHourDTO workingHour = service.updateWorkTime(id, workingHourDTO);
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }
}
