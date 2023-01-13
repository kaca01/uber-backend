package com.example.test.service.implementation;

import com.example.test.domain.business.WorkingHour;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.enumeration.RideStatus;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IDriverRepository;
import com.example.test.service.interfaces.ISelectionDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class SelectionDriver implements ISelectionDriver {
    List<Driver> refused = new ArrayList<>();
    HashMap<Driver, Date> whenFinishes = new HashMap<>();
    @Autowired
    IRideRepository iRideRepository;
    @Autowired
    IDriverRepository iDriverRepository;


    // TODO : driver has less than 8 working hours in the last 24 hours (with estimated time?)

    @Override
    public Driver findDriver(Ride ride, String type) {
        // here are also eliminated drivers with incompatibility of vehicle
        List<Driver> activeDrivers = getActiveDrivers(type, ride);
        if (activeDrivers.size() == 0) return null;
        List<Driver> availableDrivers = getAvailableDrivers(activeDrivers, ride);
        System.out.println("Prosao availableDrivers");
        removeFinishedForToday(availableDrivers);
        removeIncompatible(availableDrivers, ride, type);
        // if there are active available drivers, return them
        if (availableDrivers.size() > 0) {
            return findWithMinDistance(availableDrivers, ride);
        }
        // if there are no active available drivers, find drivers that do not have scheduled ride
        List<Driver> noScheduledRide = getDriversWithNoScheduledRide(activeDrivers, ride);
        System.out.println("Prosao do with no scheduled ride");
        System.out.println(noScheduledRide);
        if (noScheduledRide.size() == 0) return null;
        removeFinishedForToday(noScheduledRide);
        removeIncompatible(noScheduledRide, ride, type);
        // TODO : add driver to finish earliest
        return getFinishEarliest(noScheduledRide, ride.getEstimatedTimeInMinutes());
    }

    @Override
    public double getDistance(Location l1, Location l2) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = l1.getLongitude();
        double lon2 = l2.getLongitude();
        double lat1 = l1.getLatitude();
        double lat2 = l2.getLatitude();
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dLon = lon2 - lon1;
        double dLat = lat2 - lat1;
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dLon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    public double getDistanceFromRide(Driver driver, Ride ride) {
        Optional<Route> first = ride.getLocations().stream().findFirst();
        return getDistance(driver.getVehicle().getCurrentLocation(), first.get().getDeparture());
    }

    private List<Driver> getActiveDrivers(String type, Ride ride) {
        List<Driver> allDrivers = iDriverRepository.findAll();
        List<Driver> drivers = new ArrayList<>();
        for (Driver driver: allDrivers) {
            if (!isVehicleCompatible(type, driver)) continue;
            if (driver.isActive()) drivers.add(driver);
        }
        return drivers;
    }

    // returns active drivers with no current ride
    private List<Driver> getAvailableDrivers(List<Driver> activeDrivers, Ride newRide) {
        List<Driver> noScheduled = getDriversWithNoScheduledRide(activeDrivers, newRide);
        List<Driver> drivers = new ArrayList<>();
        for (Driver driver : activeDrivers) {
            Ride ride = iRideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, driver.getId());
            if (ride == null) {
                if (noScheduled.contains(driver)) drivers.add(driver);
            }
        }
        return drivers;
    }

    private List<Driver> getDriversWithNoScheduledRide(List<Driver> activeDrivers, Ride newRide) {
        List<Driver> drivers = new ArrayList<>();
        boolean overlaps = false;
        for (Driver driver : activeDrivers) {
            List<Ride> rides = iRideRepository.findRidesByStatusAndDriver_Id(RideStatus.ACCEPTED, driver.getId());
            if (rides == null) {
                drivers.add(driver);
                continue;
            }
            for (Ride ride : rides){
                if (checkIfRidesOverlap(ride, newRide)) {
                    overlaps = true;
                    break;
                }
            }
            if (!overlaps) drivers.add(driver);
            else overlaps = false;
        }
        return drivers;
    }

    private boolean checkIfRidesOverlap(Ride firstRide, Ride secondRide) {
        Date firstRideStart = firstRide.getStartTime();
        Date firstRideEnd = addMinutesToDate(firstRide.getStartTime(), (long) firstRide.getEstimatedTimeInMinutes());
        Date secondRideStart = secondRide.getStartTime();
        Date secondRideEnd = addMinutesToDate(secondRide.getStartTime(), (long) secondRide.getEstimatedTimeInMinutes());
        if (firstRideStart.before(secondRideStart) && firstRideEnd.before(secondRideStart)) return false;
        else return !firstRideStart.after(secondRideEnd) || !firstRideEnd.after(secondRideEnd);
    }

    @Override
    public Date addMinutesToDate(Date date, long minutes) {
        long start = date.getTime();
        return new Date(start + (minutes * 60000));
    }

    // removes drivers that have more than 8 hours in the last 24 hours
    private void removeFinishedForToday(List<Driver> drivers) {
        drivers.removeIf(driver -> isFinishedForToday(driver.getWorkingHours()));
    }

    // checks if driver has more than 8 hours in the last 24 hours
    private boolean isFinishedForToday(Set<WorkingHour> workingHours) {
        // this should be called from the function that chooses one driver
        double sumOfHours = 0;
        Date date = find24HoursAgo();
        for (WorkingHour workingHour : workingHours) {
            Date workingHourStarts = workingHour.getStart();
            Date workingHourEnds;
            if (workingHour.getEnd() != null) workingHourEnds = workingHour.getEnd();
            else workingHourEnds = new Date();
            if (workingHourStarts.after(date)) {
                sumOfHours += calculateHoursDifference(workingHourStarts, workingHourEnds);
            } else if (workingHourEnds.after(date)) {
                sumOfHours += calculateHoursDifference(date, workingHourEnds);
            }
        }
        return sumOfHours >= 8;
    }

    private long calculateHoursDifference(Date startDate, Date endDate) {
        long differenceInTime = endDate.getTime() - startDate.getTime();

        return TimeUnit.MILLISECONDS.toHours(differenceInTime);
    }

    private long calculateMinutesDifference(Date startDate, Date endDate) {
        long differenceInTime = endDate.getTime() - startDate.getTime();

        return TimeUnit.MILLISECONDS.toMinutes(differenceInTime);
    }

    private Date find24HoursAgo() {
        Instant instant = Instant.now().minus(24, ChronoUnit.HOURS);
        return Date.from(instant);
    }

    private List<Driver> removeIncompatible(List<Driver> drivers, Ride ride, String type) {
        List<Driver> validDrivers = new ArrayList<>();
        for (Driver driver: drivers) {
            if (!isVehicleCompatible(type, driver)) continue;
            if (!isTransportCompatible(ride, driver.getVehicle())) continue;
            validDrivers.add(driver);
        }
        return validDrivers;
    }

    private boolean isVehicleCompatible(String vehicleType, Driver driver) {
        return (driver.getVehicle().getType().getName().toString().equals(vehicleType));
    }

    private boolean isTransportCompatible(Ride ride, Vehicle vehicle) {
        if (ride.isBabyTransport()) if(!vehicle.isBabyTransport()) return false;
        if (ride.isPetTransport()) return vehicle.isPetTransport();
        return true;
    }

    private HashMap<Driver, Double> getAllDistances(List<Driver> drivers, Ride ride) {
        HashMap<Driver, Double> distances = new HashMap<>();
        for (Driver driver : drivers) distances.put(driver, getDistanceFromRide(driver, ride));
        return distances;
    }

    private Driver findWithMinDistance(List<Driver> drivers, Ride ride) {
        HashMap<Driver, Double> distances = getAllDistances(drivers, ride);
        Driver minDriver = null;
        for (Driver driver : distances.keySet()) {
            if (minDriver == null) minDriver = driver;
            else if (distances.get(minDriver) > distances.get(driver)) minDriver = driver;
        }
        return minDriver;
    }

    private Date findWhenFinishes(Driver driver, double estimatedTime) {
        List<Ride> rides = iRideRepository.findRidesByStatusAndDriver_Id(RideStatus.ACCEPTED, driver.getId());
        List<Ride> currentRide = iRideRepository.findRidesByStatusAndDriver_Id(RideStatus.ACTIVE, driver.getId());
        rides.add(currentRide.get(0));
        rides.sort(new sortItems());
        for (int i = 0; i < rides.size() - 1; i++) {
            Date estimatedEndTime = addMinutesToDate(rides.get(i).getStartTime(), (long)rides.get(i).getEstimatedTimeInMinutes());
            double minutes = calculateMinutesDifference(estimatedEndTime, rides.get(i+1).getStartTime());
            if (minutes > estimatedTime) {
                whenFinishes.put(driver, estimatedEndTime);
                return estimatedEndTime;
            }
        }
        // TODO : then find how many minutes driver has between two accepted rides
        // TODO : if there is enough time for new ride, save in hash map when finishes ride
        Date end;
        if (rides.size() > 1) end = addMinutesToDate(rides.get(rides.size()-1).getStartTime(),
                                         (long)rides.get(rides.size()-1).getEstimatedTimeInMinutes());
        else end = addMinutesToDate(rides.get(0).getStartTime(),
                (long)rides.get(0).getEstimatedTimeInMinutes());
        whenFinishes.put(driver, end);
        return end;
    }

    private Driver getFinishEarliest(List<Driver> drivers, double estimatedTime) {
        Driver minDriver = null;
        Date minFinishes = null;
        refused.add(iDriverRepository.findById(6L));
        for (Driver driver : drivers) {
            Date whenFinished = findWhenFinishes(driver, estimatedTime);
            if (minDriver == null) {
                if (!refused.contains(driver)) {
                    minDriver = driver;
                    minFinishes = whenFinished;
                }
            }
            else {
                if (minFinishes.after(whenFinished)) {
                    if (!refused.contains(driver)) {
                        minDriver = driver;
                        minFinishes = whenFinished;
                    }
                }
            }
        }
        return minDriver;
    }

    public void refuseRide(Driver driver) {
        refused.add(driver);
    }
}

class sortItems implements Comparator<Ride> {
    // Method of this class
    // @Override
    public int compare(Ride a, Ride b)
    {
        // Returning the value after comparing the objects
        // this will sort the data in Ascending order
        Date firstEndTime =  new Date(a.getStartTime().getTime() + ((long)a.getEstimatedTimeInMinutes() * 60000));
        Date secondEndTime =  new Date(b.getStartTime().getTime() + ((long)b.getEstimatedTimeInMinutes() * 60000));
        return firstEndTime.compareTo(secondEndTime);
    }
}
