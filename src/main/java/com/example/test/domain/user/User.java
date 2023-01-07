package com.example.test.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @JsonIgnore
    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @Column(name = "blocked", nullable=false)
    private boolean blocked;

    @Column(name = "active", nullable=false)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                String address, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
