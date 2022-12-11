package com.example.test.service.interfaces;

import com.example.test.domain.ride.Location;
import com.example.test.domain.vehicle.Vehicle;
import com.example.test.dto.UnregisteredUserDTO;

import java.util.Collection;
import java.util.List;

public interface IUnregisteredUserService {

    public List<Double> getEstimationTimeAndCost(UnregisteredUserDTO unregisteredUserDTO);
}
