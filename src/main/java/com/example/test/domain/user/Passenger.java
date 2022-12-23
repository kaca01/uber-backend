package com.example.test.domain.user;

import com.example.test.domain.ride.Location;
import com.example.test.dto.user.UserDTO;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@Entity
public class Passenger extends User {
    @OneToMany()
    private Set<Location> favoriteLocations = new HashSet<>();

    public Passenger(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                     String address, String password, boolean blocked, boolean active,
                     Set<Location> favoriteLocations) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, blocked, active);
        this.favoriteLocations = favoriteLocations;
    }

    public Passenger(UserDTO passengerDTO)
    {
        this.setName(passengerDTO.getName());
        this.setSurname(passengerDTO.getSurname());
        this.setEmail(passengerDTO.getEmail());
        this.setProfilePicture(passengerDTO.getProfilePicture());
        this.setAddress(passengerDTO.getAddress());
        this.setTelephoneNumber(passengerDTO.getTelephoneNumber());
        this.setPassword(passengerDTO.getPassword());
    }
}
