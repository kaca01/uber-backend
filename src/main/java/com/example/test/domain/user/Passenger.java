package com.example.test.domain.user;

import com.example.test.domain.ride.Location;
import com.example.test.dto.user.UserDTO;

import java.util.ArrayList;

public class Passenger extends User {
    private ArrayList<Location> favoriteLocations;

    public Passenger() {

    }

    public Passenger(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email,
                     String address, String password, boolean blocked, boolean active,
                     ArrayList<Location> favoriteLocations) {
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

    public ArrayList<Location> getFavoriteLocations() {
        return favoriteLocations;
    }

    public void setFavoriteLocations(ArrayList<Location> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "favoriteLocations=" + favoriteLocations +
                '}';
    }
}
