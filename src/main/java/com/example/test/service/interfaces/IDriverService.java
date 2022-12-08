package com.example.test.service.interfaces;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Document;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.*;

import java.util.Collection;

public interface IDriverService {

    public UserDTO insert(UserDTO driver);

    // TODO : check this later (paginated drivers???)
    public AllUsersDTO getAll();

    public UserDTO get(Long id);

    public UserDTO update(Long id, UserDTO driver);

    public Collection<DocumentDTO> getDriverDocuments(Long id);

    public Boolean deleteDriverDocument(Long id);

    public DocumentDTO insertDriverDocument(Long id, DocumentDTO document);

    public VehicleDTO getVehicle(Long id);

    public VehicleDTO insertVehicle(Long id, VehicleDTO vehicle);

    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicle);

    public AllWorkingHoursDTO getWorkTime(Long id);

    public WorkingHourDTO insertWorkTime(Long id, WorkingHourDTO workingHour);

    public Collection<Ride> getRides(Long id);

    public WorkingHourDTO getWorkTime(Long workTimeId, boolean flag);

    public WorkingHourDTO updateWorkTime(Long workTimeId);

}
