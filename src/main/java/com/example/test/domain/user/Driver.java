package com.example.test.domain.user;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@NoArgsConstructor
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@Entity
public class Driver extends User {

    @Column(name = "drivingLicense")
    @Length(max = 100)
    private String drivingLicense;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Set<WorkingHour> workingHours = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    public Driver(UserDTO driverDTO) {
        this(driverDTO.getId(), driverDTO.getName(), driverDTO.getSurname(), driverDTO.getProfilePicture(),
                driverDTO.getTelephoneNumber(), driverDTO.getEmail(), driverDTO.getAddress());
    }

    public Driver(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                  String address) {
        super(id, name, surname, profilePicture,telephoneNumber,email,address);
    }

    public Driver(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                  String address, String password) {
        super(id, name, surname, profilePicture,telephoneNumber,email,address, password);
    }

    public Driver(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                  String address, String password, boolean blocked, boolean active,
                  String drivingLicense, Set<WorkingHour> workingHours, Vehicle vehicle) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, new Timestamp(new Date().getTime()), blocked, active, new ArrayList<>());
        this.drivingLicense = drivingLicense;
        this.workingHours = workingHours;
        this.vehicle = vehicle;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }
    @Override
    public String getUsername() {
        return this.getEmail();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isBlocked();
    }
}
