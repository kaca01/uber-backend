package com.example.test.service.implementation;

import com.example.test.domain.business.Report;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Role;
import com.example.test.dto.AllDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements IReportService {

    @Autowired
    ISelectionDriver iSelectionDriver;

    @Autowired
    IRideService iRideService;

    @Autowired
    IPassengerService iPassengerService;

    @Autowired
    IDriverService iDriverService;

    @Autowired
    IUserService iUserService;

    @Override
    public AllDTO<Report> createCrossedKmReport(Long userId) {
        Role role = iUserService.getRole(userId);
        List<Ride> rides = new ArrayList<>();
        if (role.getName().equals("ROLE_ADMIN")) {

        }
        return null;
    }

    @Override
    public AllDTO<Report> createNumOfRidesReport(Long userId) {

        return null;
    }

    @Override
    public AllDTO<Report> createSumOfMoneyReport(Long userId) {

        return null;
    }
}
