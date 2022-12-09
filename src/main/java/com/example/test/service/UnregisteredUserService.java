package com.example.test.service;

import com.example.test.dto.UnregisteredUserDTO;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnregisteredUserService implements IUnregisteredUserService {

    @Override
    public List<Double> getEstimationTimeAndCost(UnregisteredUserDTO unregisteredUserDTO) {
        return null;
    }
}
