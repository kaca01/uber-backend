package com.example.test.service;

import com.example.test.dto.user.UnregisteredUserDTO;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnregisteredUserService implements IUnregisteredUserService {

    @Override
    public List<Integer> getEstimationTimeAndCost(UnregisteredUserDTO unregisteredUserDTO) {
        double departureLatitude = unregisteredUserDTO.getLocations().get(0).getDeparture().getLatitude();
        double departureLongitude = unregisteredUserDTO.getLocations().get(0).getDeparture().getLongitude();
        double destinationLatitude = unregisteredUserDTO.getLocations().get(0).getDestination().getLatitude();
        double destinationLongitude = unregisteredUserDTO.getLocations().get(0).getDestination().getLongitude();
        double kms = calculateKilometersNumber(departureLatitude, destinationLatitude,  departureLongitude,
                                                                                    destinationLongitude);
        List<Integer> estimationValues = new ArrayList<>();
        int estimatedTime = calculateEstimationTime(kms);
        estimationValues.add(estimatedTime);
        // price_by_type_of_vehicle + number_of_kilometers*120
        int estimationCost;
        switch (unregisteredUserDTO.getVehicleType().toUpperCase()) {
            case "STANDARD":
                estimationCost = (int) (100+kms*120);
                estimationValues.add(estimationCost);
                return estimationValues;
            case "LUXURY":
                estimationCost = (int) (500+kms*120);
                estimationValues.add(estimationCost);
                return estimationValues;
            case "VAN":
                estimationCost = (int) (1000+kms*120);
                estimationValues.add(estimationCost);
                return estimationValues;
        }
        return null;
    }

    private double calculateKilometersNumber(double lat1, double lat2, double lon1, double lon2) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
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

    private int calculateEstimationTime(double kms) {
        double kms_per_min = 0.5;
        double min_taken = kms / kms_per_min;
        return (int) min_taken;
    }
}
