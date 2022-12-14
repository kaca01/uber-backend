package com.example.test.domain.user;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
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

    public User() {

    }

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

    public User(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                String address, String password, boolean blocked, boolean active) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.blocked = blocked;
        this.active = active;
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", blocked=" + blocked +
                ", active=" + active +
                '}';
    }
}
