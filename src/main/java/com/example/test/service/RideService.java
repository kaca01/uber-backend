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

        ArrayList<Passenger> passengers = new ArrayList<>();

        PassengerService passengerService = new PassengerService();
        for (UserDTO u : rideDTO.getPassengers()) {
            Passenger p = passengerService.findUserById(u.getId());
            if (p == null)
            {
                return null;
            }
            passengers.add(p);
        }
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
        ride.setStatus(RideStatus.PENDING);

        return ride;
    }

    @Override
    public Ride findDriversActiveRide(Long id) {
        return null;
    }

    @Override
    public Ride findPassengersActiveRide(Long id) {
        return null;
    }

    @Override
    public Ride findRideById(Long id) {
        return null;
    }

    @Override
    public Boolean cancelExistingRide(Long id) {
        return false;
    }

    @Override
    public Message setPanic(String reason, Long id) {
        return null;
    }

    @Override
    public Ride acceptRide(Long id) {
        return null;
    }

    @Override
    public Ride endRide(Long id) {
        return null;
    }

    @Override
    public Ride cancelRide(String reason, Long id) {
        return null;
    }
}
