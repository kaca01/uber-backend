package com.example.test.domain.user;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.user.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@Entity
public class Driver extends User {

    @Column(name = "drivingLicense", nullable = false)
    private int drivingLicense;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Set<WorkingHour> workingHours = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER)
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
                  int drivingLicense, Set<WorkingHour> workingHours, Vehicle vehicle) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, blocked, active);
        this.drivingLicense = drivingLicense;
        this.workingHours = workingHours;
        this.vehicle = vehicle;
    }
}
