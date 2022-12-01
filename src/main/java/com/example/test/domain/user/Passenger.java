package com.example.test.domain.user;

import com.example.test.domain.user.User;

import java.util.ArrayList;

public class Passenger extends User {
    private ArrayList<Integer> rides;
    private ArrayList<Integer> favoriteLocations;

    public Passenger() {

    }

    public Passenger(int id, String name, String phone, String email, String address, String password, boolean blocked,
                     ArrayList<Integer> rides, ArrayList<Integer> favoriteLocations) {
        super(id, name, phone, email, address, password, blocked);
        this.rides = rides;
        this.favoriteLocations = favoriteLocations;
    }

    public ArrayList<Integer> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Integer> rides) {
        this.rides = rides;
    }

    public ArrayList<Integer> getFavoriteLocations() {
        return favoriteLocations;
    }

    public void setFavoriteLocations(ArrayList<Integer> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "rides=" + rides +
                ", favoriteLocations=" + favoriteLocations +
                '}';
    }
}
