package com.example.test.dto.user;

import com.example.test.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;
    private String password;

    private boolean isBlocked;

    public UserDTO(User user)
    {
        this(user.getId(), user.getName(), user.getSurname(), user.getProfilePicture(), user.getTelephoneNumber(),
                user.getEmail(), user.getAddress(), user.isBlocked());
    }

    // response
    public UserDTO(Long id, String name, String surname, String profilePicture, String telephoneNumber,
                   String email, String address, boolean isBlocked) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.isBlocked = isBlocked;
    }

    // response
    public UserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    // request
    public UserDTO(String name, String surname, String profilePicture, String telephoneNumber,
                   String email, String address, String password) {
        super();
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
