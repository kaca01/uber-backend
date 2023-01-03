package com.example.test.controller;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.user.Document;
import com.example.test.domain.user.Driver;
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
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO driverDTO) throws Exception{
        UserDTO returnedDriver = service.insert(driverDTO);
        if (returnedDriver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDTO>(returnedDriver, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<UserDTO>> getAll() throws Exception{
        AllDTO<UserDTO> drivers = service.getAll();

        if (drivers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable Long id) throws Exception {
        UserDTO driver = service.get(id);
        if (driver == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        // TODO : add 400 status
        return new ResponseEntity<UserDTO>(driver, HttpStatus.OK);
    }

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

    @GetMapping(value = "/{id}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDTO>> getDriverDocuments(@PathVariable Long id) throws Exception {
        List<DocumentDTO> driverDocuments = service.getDriverDocuments(id);
        // TODO : add 400 status
        if (driverDocuments == null) {
            return new ResponseEntity<List<DocumentDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<DocumentDTO>>(driverDocuments, HttpStatus.OK);
    }

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

    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDTO<RideDTO>> getRides(@PathVariable Long id) throws Exception {
        AllDTO<RideDTO> rides = service.getRides(id);
        // TODO : add 400 status
        if (rides == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    @GetMapping(value = "/working-hour/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkingHourDTO> getWorkTime(@PathVariable Long id) throws Exception {
        WorkingHourDTO workingHour = service.getWorkTime(id);
        // TODO : add 400 status
        if (workingHour == null) {
            return new ResponseEntity<WorkingHourDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkingHourDTO>(workingHour, HttpStatus.OK);
    }

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
