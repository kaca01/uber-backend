package com.example.test.domain.account;

import com.example.test.domain.user.Driver;
import com.example.test.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserChanges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Driver driver;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "surname", nullable=false)
    private String surname;

    @Column(name = "profilePicture")
    private String profilePicture;

    @Column(name = "telephoneNumber")
    private String telephoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address")
    private String address;

    public UserChanges(UserDTO driverDTO, Driver driver) {
        this(driverDTO.getId(), driver, driverDTO.getName(), driverDTO.getSurname(), driverDTO.getProfilePicture(),
                driverDTO.getTelephoneNumber(), driverDTO.getEmail(), driverDTO.getAddress());
    }
}
