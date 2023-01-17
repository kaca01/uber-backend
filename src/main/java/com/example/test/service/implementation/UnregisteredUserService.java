package com.example.test.service.implementation;

import com.example.test.dto.user.UnregisteredUserDTO;
import com.example.test.service.interfaces.ISelectionDriver;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnregisteredUserService implements IUnregisteredUserService {

    @Autowired
    ISelectionDriver iSelectionDriver;

    @Override
    public List<Integer> getEstimationTimeAndCost(UnregisteredUserDTO unregisteredUserDTO) {
        double kms = iSelectionDriver.getDistance(unregisteredUserDTO.getLocations().get(0).getDeparture(),
                                                  unregisteredUserDTO.getLocations().get(0).getDestination());
        List<Integer> estimationValues = new ArrayList<>();
        int estimatedTime = iSelectionDriver.calculateEstimationTime(kms);
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


}
