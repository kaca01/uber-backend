package com.example.test.service;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.RideDTO;
import com.example.test.dto.UserDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.service.interfaces.IRideService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class RideService implements IRideService {

    // trebalo bi da nadje odgovarajuceg dostupnog vozaca za voznju
    @Override
    public Ride insert(Ride ride, RideDTO rideDTO) {

        setRideData(ride);
        ArrayList<Passenger> passengers = new ArrayList<>();

        // metoda treba da pronalazi postojeceg passengera u bazi i stvalja ga u ride, a ne kreira novog
        for (UserDTO u : rideDTO.getPassengers()) {
            Passenger p = new Passenger(u);
            passengers.add(p);
        }
        ride.setPassengers(passengers);
        ride.setStatus(RideStatus.PENDING);

        return ride;
    }

    @Override
    public Ride findDriversActiveRide(Long id) {
        //if(id > 5) return null;

        Ride ride = new Ride();
        setRideData(ride);

        return ride;
    }

    @Override
    public Ride findPassengersActiveRide(Long id) {
        //if(id > 4) return null;

        Ride ride = new Ride();
        setRideData(ride);

        return ride;
    }

    @Override
    public Ride findRideById(Long id) {
        Ride ride = new Ride();
        setRideData(ride);
        ride.setStatus(RideStatus.PENDING);

        return ride;
    }

    @Override
    public Ride cancelExistingRide(Long id) {
        //if(id > 4) return null;

        Ride ride = findRideById(id);
        setRideData(ride);
        ride.setStatus(RideStatus.REJECTED);
        return ride;
    }

    //the user will be used from the token
    @Override
    public Message setPanic(String reason, Long id)
    {
        //if(id > 4) return null;

        Ride ride = findRideById(id);
        Message panic = new Message((long)15, ride.getDriver(), null, reason, new Date(), MessageType.PANIC, ride);
        return panic;
    }

    @Override
    public Ride acceptRide(Long id) {
        Ride ride = findRideById(id);
        setRideData(ride);
        ride.setStatus(RideStatus.ACCEPTED);
        return ride;
    }

    @Override
    public Ride endRide(Long id) {
        Ride ride = findRideById(id);
        setRideData(ride);
        ride.setStatus(RideStatus.FINISHED);
        return ride;
    }

    @Override
    public Ride cancelRide(String reason, Long id) {
        Ride ride = findRideById(id);
        setRideData(ride);
        ride.setStatus(RideStatus.REJECTED);
        ride.getRejection().setReason(reason);
        return ride;
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
                true, 567, new ArrayList<WorkingHour>(), v));
        ride.setVehicle(v);
        v.getType().setName(VehicleTypeName.STANDARD);  //todo dont hardcode this
        ride.setEstimatedTimeInMinutes(5);
        ride.setRejection(new Rejection());
        ride.setStatus(RideStatus.ACTIVE);
        return ride;
    }
}
