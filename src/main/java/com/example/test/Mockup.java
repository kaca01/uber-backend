package com.example.test;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Note;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.domain.vehicle.VehicleType;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.ReviewType;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;

import java.util.ArrayList;
import java.util.Date;

public class Mockup {
    public final ArrayList<Vehicle> vehicles = createVehicle();
    public final ArrayList<User> users = createUsers();
    public final ArrayList<Note> notes = createNotes();
    public final ArrayList<Passenger> passengers = createPassenger();
    public final ArrayList<Driver> drivers = createDrivers();
    public final ArrayList<Rejection> rejections = createRejection();
    public final ArrayList<Review> reviews = createReview();
    public final ArrayList<Ride> rides = createRide();
    public final ArrayList<Message> messages = createMessages();

    private ArrayList<User> createUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        Passenger p1 = new Passenger(1L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.peric@email.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p2 = new Passenger(2L, "Mika", "Mikic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "123", false, true, null);
        Passenger p3 = new Passenger(3L, "Neko", "Nekic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com", "Nikole Pasica 25", "456", false, true, null);
        Passenger p4 = new Passenger(123L, "Bosko", "Radojcic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com", "Nikole Pasica 25", "789", false, true, null);
        users.add(p1);
        users.add(p2);
        users.add(p3);
        users.add(p4);
        Driver d1 = new Driver(4L, "Nikola", "Nikic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica@gmail.com", "Nikole Pasica 25", "213", false, true, 7, null, vehicles.get(0));
        Driver d2 = new Driver(5L, "Sandra", "Nastasic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera@gmail.com", "Nikole Pasica 25", "sifra", false, true, 4576, null, vehicles.get(1));
        Driver d3 = new Driver(6L, "Sinisa", "Nemanjic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko@gmail.com", "Nikole Pasica 25", "nekasifra", false, true, 65757, null, vehicles.get(2));
        Driver d4 = new Driver(7L, "Boris", "Sandric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko@gmail.com", "Nikole Pasica 25", "111", false, true,4324345, null, vehicles.get(3));
        users.add(d1);
        users.add(d2);
        users.add(d3);
        users.add(d4);
        return users;
    }

    private ArrayList<Passenger> createPassenger()
    {
        ArrayList<Passenger> passengers = new ArrayList<>();
        Passenger p1 = new Passenger(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p2 = new Passenger(2L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p3 = new Passenger(3L, "Neko", "Nekic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p4 = new Passenger(123L, "Bosko", "Radojcic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        passengers.add(p1);
        passengers.add(p2);
        passengers.add(p3);
        passengers.add(p4);
        return passengers;
    }

    private ArrayList<Driver> createDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();
        Driver d1 = new Driver(4L, "Nikola", "Nikic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, 7,null, vehicles.get(0));
        Driver d2 = new Driver(5L, "Sandra", "Nastasic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, 4576, null, vehicles.get(1));
        Driver d3 = new Driver(6L, "Sinisa", "Nemanjic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, 65757, null, vehicles.get(2));
        Driver d4 = new Driver(7L, "Boris", "Sandric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true,4324345, null, vehicles.get(3));
        drivers.add(d1);
        drivers.add(d2);
        drivers.add(d3);
        drivers.add(d4);
        return drivers;
    }

    private ArrayList<Vehicle> createVehicle()
    {
        Vehicle v1 = new Vehicle(1L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v2 = new Vehicle(2L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v3 = new Vehicle(3L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        Vehicle v4 = new Vehicle(4L, new VehicleType(1L, VehicleTypeName.STANDARD, 42), "opel 21", "U3dhZ2dlciByb2Nrcw==", 2, new Location(1L, 24.24, 27.27, "Petra Perica 33"), true, false);
        ArrayList<Vehicle> ps = new ArrayList<>();
        ps.add(v1);
        ps.add(v2);
        ps.add(v3);
        ps.add(v4);
        return ps;
    }

    private ArrayList<Rejection> createRejection()
    {
        Rejection r1 = new Rejection(121L, "odbacena voznja", users.get(0), new Date());
        Rejection r2 = new Rejection(230L, "problemi sa putnikom", users.get(2), new Date());
        ArrayList<Rejection> r = new ArrayList<>();
        r.add(r1);
        r.add(r2);
        return r;
    }

    private ArrayList<Note> createNotes()
    {
        Note n1 = new Note(1L, new Date(), "nesto nije u redu", users.get(0));
        Note n2 = new Note(2L, new Date(), "nesto nije u redu", users.get(1));
        Note n3 = new Note(3L, new Date(), "nesto nije u redu", users.get(2));
        Note n4 = new Note(4L, new Date(), "nesto nije u redu", users.get(3));
        ArrayList<Note> n = new ArrayList<>();
        n.add(n1);
        n.add(n2);
        n.add(n3);
        n.add(n4);
        return n;
    }

    private ArrayList<Review> createReview()
    {
        Review v1 = new Review(1L, 3, "dirty vehicle", passengers.get(0), ReviewType.VEHICLE);
        Review v2 = new Review(2L, 5, "great vehicle", passengers.get(3), ReviewType.VEHICLE);
        Review v3 = new Review(3L, 1, "rude driver", passengers.get(2), ReviewType.DRIVER);
        Review v4 = new Review(4L, 5, "kind driver", passengers.get(1), ReviewType.DRIVER);
        Review v5 = new Review(5L, 5, "lovely ride", passengers.get(1), ReviewType.VEHICLE);
        ArrayList<Review> ps = new ArrayList<>();
        ps.add(v1);
        ps.add(v2);
        ps.add(v3);
        ps.add(v4);
        ps.add(v5);
        return ps;
    }

    private ArrayList<Ride> createRide()
    {
        /*Ride v1 = new Ride(1L, new Date(), new Date(), 98.8, 25.32, vehicles.get(1), drivers.get(0), passengers, reviews, RideStatus.ACCEPTED, rejections.get(0), true, false, null);
        Ride v2 = new Ride(2L, new Date(), new Date(), 328.8, 2234.76, vehicles.get(2), drivers.get(1), passengers, reviews, RideStatus.ACCEPTED, rejections.get(1), false, false, null);
        Ride v3 = new Ride(123L, new Date(), new Date(), 328.8, 2234.76, vehicles.get(2), drivers.get(2), passengers, reviews, RideStatus.ACCEPTED, rejections.get(0), false, false, null);
        */ArrayList<Ride> ps = new ArrayList<>();
        /*ps.add(v1);
        ps.add(v2);
        ps.add(v3);*/
        return ps;
    }

    private ArrayList<Message> createMessages()
    {
        /*Message m1 = new Message(1L, users.get(1), users.get(2), "sve najlepse", new Date(), MessageType.RIDE, rides.get(0));
        Message m2 = new Message(2L, users.get(2), users.get(1), "lepa voznja", new Date(), MessageType.SUPPORT, rides.get(0));
        Message m3 = new Message(3L, users.get(3), users.get(0), "odlican vozac", new Date(), MessageType.PANIC, rides.get(1));
        Message m4 = new Message(4L, users.get(0), users.get(1), "vozilo je bilo prljavo", new Date(), MessageType.RIDE, rides.get(1));*/
        ArrayList<Message> messages = new ArrayList<>();
        /*messages.add(m1);
        messages.add(m2);
        messages.add(m3);
        messages.add(m4);*/
        return messages;
    }
}
