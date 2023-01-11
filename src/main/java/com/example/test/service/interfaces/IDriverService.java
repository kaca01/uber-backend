package com.example.test.service.interfaces;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.AllDTO;
import com.example.test.dto.business.WorkingHourDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.DocumentDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.vehicle.VehicleDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface IDriverService {

    public UserDTO insert(UserDTO driver);

    // TODO : check this later (paginated drivers???)
    public AllDTO<UserDTO> getAll();

    public UserDTO get(Long id);

    public UserDTO update(Long id, UserDTO driverDTO);

    public List<DocumentDTO> getDriverDocuments(Long id);

    public DocumentDTO insertDriverDocument(Long id, DocumentDTO driverDocument);

    public DocumentDTO deleteDriverDocument(Long id);

    public VehicleDTO getVehicle(Long id);

    public VehicleDTO insertVehicle(Long id, VehicleDTO vehicle);

    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicle);

    public AllDTO<WorkingHourDTO> getWorkTimes(Long id);

    public WorkingHourDTO insertWorkTime(Long id, WorkingHourDTO workingHour) throws ParseException;

    public AllDTO<RideDTO> getRides(Long id);

    public WorkingHourDTO getWorkTime(Long workTimeId);

    public WorkingHourDTO updateWorkTime(Long workTimeId, WorkingHourDTO workingHour) throws ParseException;

    public List<Driver> findAvailable(Ride ride);
}