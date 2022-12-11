package com.example.test.service.interfaces;

import com.example.test.dto.user.UnregisteredUserDTO;

import java.util.List;

public interface IUnregisteredUserService {

    public List<Double> getEstimationTimeAndCost(UnregisteredUserDTO unregisteredUserDTO);
}
