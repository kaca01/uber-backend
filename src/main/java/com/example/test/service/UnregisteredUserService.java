package com.example.test.service;

import com.example.test.dto.UnregisteredUserDTO;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnregisteredUserService implements IUnregisteredUserService {

    @Override
    public List<Double> getEstimationTimeAndCost(UnregisteredUserDTO unregisteredUserDTO) {
        List<Double> estimationValues = new ArrayList<>();
        switch (unregisteredUserDTO.getVehicleType().toUpperCase()) {
            case "STANDARDNO":
                estimationValues.add(33.0);
                estimationValues.add(550.5);
                return estimationValues;
            case "LUXURY":
                estimationValues.add(11.0);
                estimationValues.add(302.01);
                return estimationValues;
            case "VAN":
                estimationValues.add(64.0);
                estimationValues.add(1053.43);
                return estimationValues;
        }
        return null;
    }
}
