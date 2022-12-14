package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IPanicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class PanicService implements IPanicService{

    @Override
    public List<Message> getAll()
    {
        ArrayList<Message> panics = new ArrayList<>();

        Ride ride = new Ride();
        setRideData(ride);
        Message m1 = new Message((long)15, ride.getDriver(), null, "reason1", new Date(), MessageType.PANIC, ride);
        Message m2 = new Message((long)16, ride.getDriver(), null, "reason2", new Date(), MessageType.PANIC, ride);
        Message m3 = new Message((long)17, ride.getDriver(), null, "reason3", new Date(), MessageType.PANIC, ride);

        panics.add(m1);
        panics.add(m2);
        panics.add(m3);
        return panics;
    }

    private Ride setRideData(Ride ride)
    {
        ArrayList<Passenger> passengers = new ArrayList<>();
        Passenger p1 = new Passenger(1L, "Mica", "Micic", "U3dhZ2dlciByb2Nrcw==", "+381123123", "mica.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        Passenger p2 = new Passenger(2L, "Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123", "pera.micic@gmail.com", "Nikole Pasica 25", "sifra123", false, true, null);
        passengers.add(p1);
        passengers.add(p2);
        ride.setPassengers(passengers);
        ride.setStartTime(new Date());
        ride.setEndTime(new Date());
        ride.setTotalCost(1235);
        Vehicle v = new Vehicle();
        ride.setDriver(new Driver((long)123, "Vozac", "Vozacevic", "jkavajnvan",
                "+381 789456","email", "Neka adresa", "sifra", false,
                true, 567, new HashSet<>(), v));
        ride.setVehicle(v);
        v.getType().setName(VehicleTypeName.STANDARD);  //todo dont hardcode this
        ride.setEstimatedTimeInMinutes(5);
        ride.setRejection(new Rejection());
        ride.setStatus(RideStatus.ACTIVE);
        return ride;
    }
}
