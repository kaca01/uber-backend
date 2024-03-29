package com.example.test.dto.user;

import com.example.test.domain.user.Driver;
import com.example.test.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    @JsonInclude( JsonInclude.Include.NON_NULL)
    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String name;
    @JsonInclude( JsonInclude.Include.NON_NULL)
    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String surname;
    @JsonInclude( JsonInclude.Include.NON_NULL)
    private String profilePicture;
    @JsonInclude( JsonInclude.Include.NON_NULL)
    @Length(max = 18, message = "Max 18 characters allowed for telephone number!")
    private String telephoneNumber;
    @NotNull
    @NotEmpty
    @Length(max = 100)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Bad email format.")
    private String email;
    @JsonInclude( JsonInclude.Include.NON_NULL)
    @NotNull
    @NotEmpty
    @Length(max = 100, message = "Max 100 characters allowed for address!")
    private String address;
    @JsonInclude()
    private String password;
    private boolean isBlocked;
    private boolean isChanged;
    private String drivingLicense;

    public UserDTO(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                   String address, String password, boolean isBlocked, boolean isChanged, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isChanged = isChanged;
        this.drivingLicense = licenseNumber;
    }

    public UserDTO(User user)
    {
        this(user.getId(), user.getName(), user.getSurname(), user.getProfilePicture(), user.getTelephoneNumber(),
                user.getEmail(), user.getAddress(), user.isBlocked());
    }

    public UserDTO(Driver driver) {
        this(driver.getId(), driver.getName(), driver.getSurname(), driver.getProfilePicture(), driver.getTelephoneNumber(),
                driver.getEmail(), driver.getAddress(), driver.isBlocked());
        this.isChanged = driver.isChanges();
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
