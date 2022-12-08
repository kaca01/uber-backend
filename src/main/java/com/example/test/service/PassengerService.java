package com.example.test.service;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Passenger;
import com.example.test.service.interfaces.IPassengerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PassengerService implements IPassengerService {

    private ArrayList<Passenger> passengers = createPassengers();

    @Override
    public List<Passenger> getAll(Integer page, Integer size)
    {
        return passengers;
    }

    @Override
    public Passenger insert(Passenger passenger)
    {
        passenger.setId((passengers.get(passengers.size() - 1).getId()+1));
        this.passengers.add(passenger);
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger, Long passengerId)
    {
        Passenger p = findUserById(passengerId);
        p.setName(passenger.getName());
        p.setSurname(passenger.getSurname());
        p.setProfilePicture(passenger.getProfilePicture());
        p.setTelephoneNumber(passenger.getTelephoneNumber());
        p.setEmail(passenger.getEmail());
        p.setAddress(passenger.getAddress());
        p.setId(passengerId);
        p.setPassword(passenger.getPassword());
        return p;
    }

    @Override
    public List<Ride> getRidesByPassenger(Long passengerId) {
        return null;
    }

    @Override
    public Passenger findUserById(Long id)
    {
        for (Passenger p : passengers)
        {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    //activationId == id of the passenger
    //treba da ako nije isteklo vrijeme, da user-u prebacim aktivnost na active
    @Override
    public Boolean activatePassenger(Long activationId) {
        return false;
    }

    private ArrayList<Passenger> createPassengers()
    {
        Passenger p1 = new Passenger(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p2 = new Passenger(2L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p3 = new Passenger(3L, "Neko", "Nekic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "neko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p4 = new Passenger(4L, "Bosko", "Radojcic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "bosko.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        ArrayList<Passenger> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);
        return ps;
    }
}
