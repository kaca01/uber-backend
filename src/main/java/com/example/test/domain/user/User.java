package com.example.test.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class User {
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
    @Column(name = "email", nullable = true, unique = true)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "password", nullable=false)
    private String password;
    @Column(name = "blocked", nullable=false)
    private boolean blocked;
    @Column(name = "active", nullable=false)
    private boolean active;

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
