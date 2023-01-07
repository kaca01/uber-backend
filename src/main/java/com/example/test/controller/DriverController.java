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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO driverDTO) throws Exception{
        UserDTO returnedDriver = service.insert(driverDTO);
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllDTO<UserDTO>> getAll() throws Exception{
        AllDTO<UserDTO> drivers = service.getAll();

        if (drivers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable Long id) throws Exception {
        UserDTO driver = service.get(id);
        if (driver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        // TODO : add 400 status
        return new ResponseEntity<UserDTO>(driver, HttpStatus.OK);
    }

    //TODO
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update (@PathVariable Long id, @RequestBody UserDTO driverDTO) throws Exception {
        UserDTO returnedDriver = service.update(id, driverDTO);
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        // TODO : add 400 status

        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDTO>> getDriverDocuments(@PathVariable Long id) throws Exception {
        List<DocumentDTO> driverDocuments = service.getDriverDocuments(id);
        // TODO : add 400 status
        if (driverDocuments == null) {
            return new ResponseEntity<List<DocumentDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<DocumentDTO>>(driverDocuments, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @PostMapping(value = "/{id}/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDTO> insertDriverDocuments(@PathVariable Long id,
                                                             @RequestBody DocumentDTO documentDTO)
            throws Exception{
        DocumentDTO returnedDriverDocument = service.insertDriverDocument(id, documentDTO);
        // TODO : add 400 status
        if (returnedDriverDocument == null) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DocumentDTO>(returnedDriverDocument, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @DeleteMapping(value = "/document/{id}")
    public ResponseEntity<Void> deleteDriverDocument(@PathVariable Long id) throws Exception {
        DocumentDTO document = service.deleteDriverDocument(id);
        // TODO : add 400 status
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> insertVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO)
            throws Exception {
        VehicleDTO returnedVehicle = service.insertVehicle(id, vehicleDTO);
        // TODO : add 400 status
        if (returnedVehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(returnedVehicle, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping(value = "/{id}/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO)
            throws Exception {
        VehicleDTO updatedVehicle = service.updateVehicle(id, vehicleDTO);
        // TODO : add 400 status
        if (updatedVehicle == null) {
            return new ResponseEntity<VehicleDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VehicleDTO>(updatedVehicle, HttpStatus.OK);
    }

    //returns history of the driver working hours that can be filtered by data
    //id of the driver
    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<WorkingHourDTO>> getWorkTimes(@PathVariable Long id) throws Exception{
        AllDTO<WorkingHourDTO> workingHours = service.getWorkTimes(id);
        // TODO : add 400 status
        if (workingHours == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        List<WorkingHourDTO> workingHourDTOS = new ArrayList<>();
//        for (WorkingHour workingHour : workingHours) workingHourDTOS.add(new WorkingHourDTO(workingHour));
//        AllDTO<WorkingHourDTO> allWorkingHoursDTO = new AllDTO<>(workingHours.size(), workingHourDTOS);

        return new ResponseEntity<>(workingHours, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping(value = "/{id}/working-hour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> insertWorkTime(@PathVariable Long id,
                                                         @RequestBody WorkingHourDTO workingHourDTO) throws Exception {
        Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getStart());
        Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(workingHourDTO.getEnd());
        WorkingHourDTO updatedWorkingHour = service.insertWorkTime(id, workingHourDTO);
        // TODO : add 400 status
        if (updatedWorkingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(updatedWorkingHour, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRides(@PathVariable Long id) throws Exception {
        AllDTO<RideDTO> rides = service.getRides(id);
        // TODO : add 400 status
        if (rides == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //details about the working hour of the driver
    // id of working hour
    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping(value = "/working-hour/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> getWorkTime(@PathVariable Long id) throws Exception {
        WorkingHourDTO workingHour = service.getWorkTime(id);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }

    //changing the working hours
    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping(value = "/working-hour/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> updateWorkTime(@PathVariable Long id,
                                                         @RequestBody WorkingHourDTO workingHourDTO) throws Exception {
        WorkingHourDTO workingHour = service.updateWorkTime(id, workingHourDTO);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }

}
