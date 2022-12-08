package com.example.test.domain.user;

import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.UserDTO;

import java.util.ArrayList;
import java.util.Objects;

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
        this.setId(passengerDTO.getId());
        this.setName(passengerDTO.getName());
        this.setSurname(passengerDTO.getSurname());
        this.setEmail(passengerDTO.getEmail());
        this.setProfilePicture(passengerDTO.getProfilePicture());
        this.setAddress(passengerDTO.getAddress());
        this.setTelephoneNumber(passengerDTO.getTelephoneNumber());

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
