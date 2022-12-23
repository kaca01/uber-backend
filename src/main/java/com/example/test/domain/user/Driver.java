package com.example.test.domain.user;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Driver extends User {
    // TODO add documents to upload
    private int drivingLicense;
    private ArrayList<WorkingHour> workingHours;
    private Vehicle vehicle;

    public Driver(UserDTO driverDTO) {
        this(driverDTO.getId(), driverDTO.getName(), driverDTO.getSurname(), driverDTO.getProfilePicture(),
                driverDTO.getTelephoneNumber(), driverDTO.getEmail(), driverDTO.getAddress(), driverDTO.getPassword());
    }

    public Driver(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                  String address, String password) {
        super(id, name, surname, profilePicture,telephoneNumber,email,address, password);
    }

    public Driver(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                  String address, String password, boolean blocked, boolean active,
                  int drivingLicense, ArrayList<WorkingHour> workingHours, Vehicle vehicle) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, blocked, active);
        this.drivingLicense = drivingLicense;
        this.workingHours = workingHours;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "drivingLicense=" + drivingLicense +
                ", workingHours=" + workingHours +
                ", vehicle=" + vehicle +
                '}';
    }
}
