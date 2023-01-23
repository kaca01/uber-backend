package com.example.test.service.interfaces;

import com.example.test.dto.AllDTO;
import com.example.test.dto.business.WorkingHourDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.DocumentDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.vehicle.VehicleDTO;

import java.text.ParseException;
import java.util.List;

public interface IDriverService {

    public UserDTO insertDriver(UserDTO driver);

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

    public UserDTO getChanges(Long id);

    public void addChanges(Long id, UserDTO userDTO);

    void deleteDriver(Long id);
}