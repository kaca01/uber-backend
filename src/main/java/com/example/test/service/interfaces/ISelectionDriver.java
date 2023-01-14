package com.example.test.service.interfaces;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;

import java.util.Date;

public interface ISelectionDriver {

    public Driver findDriver(Ride ride, String type);
    public double getDistance(Location l1, Location l2);
    public double getDistanceFromRide(Driver driver, Ride ride);
    public void refuseRide(Driver driver, Ride ride);
    public Date addMinutesToDate(Date date, long minutes);
    public int calculateEstimationTime(double kms);

}
