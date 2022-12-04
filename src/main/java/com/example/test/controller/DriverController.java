package com.example.test.controller;

import com.example.test.domain.business.WorkTime;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.DriverDocument;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.service.interfaces.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    IDriverService service;

    @PostMapping
    public Driver insert(@RequestBody Driver driver) {
        return service.insert(driver);
    }

    @GetMapping
    public Collection<Driver> getDrivers() {
        return service.getAll();
    }

    @PutMapping
    public Driver update (@RequestBody Driver driver) {
        return service.update(driver);
    }

    @GetMapping("/{id}")
    public Collection<DriverDocument> getDriverDocuments(@PathVariable Long id) {
        return service.getDriverDocuments(id);
    }

    @DeleteMapping("/{id}/documents")
    public boolean deleteDriverDocument(@PathVariable Long id) {
        return service.deleteDriverDocument(id);
    }

    @PostMapping("/{id}/documents")
    public Collection<DriverDocument> insertDriverDocuments(@PathVariable Long id,
                                                            @RequestBody Collection<DriverDocument> driverDocuments) {
        return service.insertDriverDocuments(id, driverDocuments);
    }

    @GetMapping("/{id}/vehicle")
    public Vehicle getVehicle(@PathVariable Long id) {
        return service.getVehicle(id);
    }

    @PostMapping("/{id}/vehicle")
    public Vehicle insertVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return service.insertVehicle(id, vehicle);
    }

    @PutMapping("/{id}/vehicle")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return service.updateVehicle(id, vehicle);
    }

    @GetMapping("/{id}/work-hours")
    public WorkTime getWorkTime(@PathVariable Long id) {
        return service.getWorkTime(id);
    }

    @PostMapping("/{id}/work-hours")
    public WorkTime insertWorkTime(@PathVariable Long id, @RequestBody WorkTime workTime) {
        return service.insertWorkTime(id, workTime);
    }

    @GetMapping("/{id}/ride")
    public Collection<Ride> getRides(@PathVariable Long id) {
        return service.getRides(id);
    }

    @GetMapping("/{id}/work-hours/{working-hour-id}")
    public WorkTime getWorkTime(@PathVariable Long id, @PathVariable Long workTimeId) {
        return service.getWorkTime(id, workTimeId);
    }

    @PutMapping("/{id}/work-hours/{working-hour-id}")
    public WorkTime updateWorkTime(@PathVariable Long id, @PathVariable Long workTimeId) {
        return service.updateWorkTime(id, workTimeId);
    }

}
