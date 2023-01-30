package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.domain.user.Driver;
import com.example.test.dto.AllDTO;
import com.example.test.dto.business.WorkingHourDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.DocumentDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.vehicle.VehicleDTO;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    @Autowired
    IDriverRepository driverRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // add new driver
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO driverDTO){
        UserDTO returnedDriver = service.insertDriver(driverDTO);
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    //used for driver logout (for simulation) and activity change to false
    @GetMapping(value ="/{id}/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> logout(@PathVariable Long id) {
        Driver user = service.changeActivity(id, false);
        this.simpMessagingTemplate.convertAndSend("/map-updates/logout", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //used for driver (for simulation) activity change to true
    @GetMapping(value ="/{id}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> active(@PathVariable Long id) {
        Driver user = service.changeActivity(id, true);
        this.simpMessagingTemplate.convertAndSend("/map-updates/driver-login", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        service.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // get all drivers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllDTO<UserDTO>> getAll(){
        AllDTO<UserDTO> drivers = service.getAll();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    //used for simulation
    @GetMapping(value = "/all/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<Driver>> getActiveDrivers() {
        AllDTO<Driver> drivers = service.getActiveDrivers();
        return new ResponseEntity<AllDTO<Driver>>(drivers, HttpStatus.OK);
    }

    // get driver by id
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable Long id) throws Exception {
        UserDTO driver = service.get(id);
        return new ResponseEntity<UserDTO>(driver, HttpStatus.OK);
    }

    //used for simulation
    @GetMapping(value = "/{id}/driver/real", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> getRealDriver(@PathVariable Long id) throws Exception {
        Driver driver = service.getRealDriver(id);
        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
    }

    // update existing driver
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update (@PathVariable Long id, @Valid @RequestBody UserDTO driverDTO) {
        UserDTO returnedDriver = service.update(id, driverDTO);
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    // get documents for driver
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDTO>> getDriverDocuments(@PathVariable Long id){
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
    public ResponseEntity<Void> deleteDriverDocument(@PathVariable Long id) {
        DocumentDTO document = service.deleteDriverDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    // get vehicle for a driver
    @GetMapping(value = "/{id}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) {
        VehicleDTO vehicle = service.getVehicle(id);
        return new ResponseEntity<VehicleDTO>(vehicle, HttpStatus.OK);
    }

    // add new vehicle for driver
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> insertVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO returnedVehicle = service.insertVehicle(id, vehicleDTO);
        return new ResponseEntity<VehicleDTO>(returnedVehicle, HttpStatus.OK);
    }

    // update vehicle
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @PutMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = service.updateVehicle(id, vehicleDTO);
        return new ResponseEntity<VehicleDTO>(updatedVehicle, HttpStatus.OK);
    }

    //returns history of the driver working hours that can be filtered by data
    //id of the driver
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<WorkingHourDTO>> getWorkTimes(@PathVariable Long id) {
        AllDTO<WorkingHourDTO> workingHours = service.getWorkTimes(id);
        return new ResponseEntity<>(workingHours, HttpStatus.OK);
    }

    // creating information about the drivers working hours
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @PostMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> insertWorkTime(@PathVariable Long id,
                                                         @Valid @RequestBody WorkingHourDTO workingHourDTO) throws Exception {
        WorkingHourDTO updatedWorkingHour = service.insertWorkTime(id, workingHourDTO);
        return new ResponseEntity<WorkingHourDTO>(updatedWorkingHour, HttpStatus.OK);
    }

    // get rides for driver
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRides(@PathVariable Long id) {
        AllDTO<RideDTO> rides = service.getRides(id);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //details about the working hour of the driver
    //id of working hour
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    // TODO : if added new role, service needs to be changed
    @GetMapping(value = "/working-hour/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> getWorkTime(@PathVariable Long id) {
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/changes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> editData(@PathVariable Long id) {
        UserDTO changes = service.getChanges(id);
        return new ResponseEntity<>(changes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping(value = "/changes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addChanges(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        service.addChanges(id, userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
