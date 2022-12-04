package com.example.test.domain.user;

import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;

import java.util.ArrayList;
import java.util.Objects;

public class Passenger extends User {
    private ArrayList<Location> favoriteLocations;

    public Passenger() {

    }

    public Passenger(int id, String name, String phone, String email, String address, String password, boolean blocked,
                     boolean active, ArrayList<Location> favoriteLocations) {
        super(id, name, phone, email, address, password, blocked, active);
        this.favoriteLocations = favoriteLocations;
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
