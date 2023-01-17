package com.example.test.tools;

import com.example.test.domain.ride.Ride;

import java.util.Comparator;
import java.util.Date;

public class SortItems implements Comparator<Ride> {
    // Method of this class
    // @Override
    public int compare(Ride a, Ride b)
    {
        // Returning the value after comparing the objects
        // this will sort the data in Ascending order
        Date firstEndTime =  new Date(a.getScheduledTime().getTime() + ((long)a.getEstimatedTimeInMinutes() * 60000));
        Date secondEndTime =  new Date(b.getScheduledTime().getTime() + ((long)b.getEstimatedTimeInMinutes() * 60000));
        return firstEndTime.compareTo(secondEndTime);
    }
}
