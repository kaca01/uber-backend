package com.example.test.scheduling;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.enumeration.RideStatus;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.tools.SortItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

public class RideScheduler {

    @Autowired
    IRideRepository rideRepository;
    @Autowired
    IDriverRepository driverRepository;


    @Scheduled(fixedDelay = 5000)
    public void updateCurrentLocationToDeparture() {

        System.out.println("Updating all vehicle's current locations to the departure point of the next ride.");

        Date date = new Date( System.currentTimeMillis() - 10000L);
        // 10 seconds after to simulate the time it takes driver to reach the location

        for (Driver d: driverRepository.findAll()){
            List<Ride> rides = rideRepository.findRidesByStatusAndDriver_Id(RideStatus.ACCEPTED, d.getId());
            rides.sort(new SortItems());
            if(rides.isEmpty()) continue;

            Ride firstRide = rides.get(0);
            if (firstRide.getScheduledTime().before(date)){
                firstRide.getVehicle().setCurrentLocation(firstRide.getLocations().stream().findFirst().get().getDeparture());
                rideRepository.save(firstRide);
            }
        }
    }
}
