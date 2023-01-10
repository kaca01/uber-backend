package com.example.test.domain.user;
import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.ride.Location;
import com.example.test.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Data
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@Entity
public class Passenger extends User {

    public Passenger(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                     String address, String password, boolean blocked, boolean active) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, new Timestamp(new Date().getTime()), blocked, active, new ArrayList<>());
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
