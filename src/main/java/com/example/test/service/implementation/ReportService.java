package com.example.test.service.implementation;

import com.example.test.domain.business.Report;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Role;
import com.example.test.dto.AllDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    IUserService passengerRepository;

    @Autowired
    IRideRepository iRideRepository;

    @Override
    public AllDTO<Report> createCrossedKmReport(Long userId) {
        List<Ride> rides = getRides(userId);
        List<Report> reports = new ArrayList<>();

        HashMap<String, Double> crossedKmsPerDay = calculateCrossedKms(rides);
        reports = parseReports(crossedKmsPerDay);
        return new AllDTO<>(reports.size(), reports);
    }

    @Override
    public AllDTO<Report> createNumOfRidesReport(Long userId) {
        List<Ride> rides = getRides(userId);
        List<Report> reports = new ArrayList<>();

        HashMap<String, Double> numOfRidesPerDay = calculateNumberOfRides(rides);
        reports = parseReports(numOfRidesPerDay);
        return new AllDTO<>(reports.size(), reports);
    }

    @Override
    public AllDTO<Report> createSumOfMoneyReport(Long userId) {
        List<Ride> rides = getRides(userId);
        List<Report> reports = new ArrayList<>();

        HashMap<String, Double> sumOfMoneyPerDay = calculateSumOfMoney(rides);
        reports = parseReports(sumOfMoneyPerDay);
        return new AllDTO<>(reports.size(), reports);
    }

    private List<Ride> getRides(Long userId) {
        Role role = iUserService.getRole(userId);
        if (role == null) throw  new NotFoundException("User with specified id not found!");

        if (role.getName().equals("ROLE_ADMIN")) {
            return iRideRepository.findByStatus(RideStatus.FINISHED);

        } else if (role.getName().equals("ROLE_PASSENGER")) {
            return iRideRepository.findRidesByStatusAndPassengers_Id(RideStatus.FINISHED, userId);

        } else {
            return iRideRepository.findRidesByStatusAndDriver_Id(RideStatus.FINISHED, userId);
        }
    }

    private boolean isDateOlderThanOneMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date oneMonthAgo = cal.getTime();
        return !date.after(oneMonthAgo);
    }

    private List<Report> parseReports(HashMap<String, Double> map) {
        List<Report> reports = new ArrayList<>();
        for (String date: map.keySet()) {
            Report report = new Report(date, map.get(date));
            reports.add(report);
        }
        return reports;
    }

    private HashMap<String, Double> calculateCrossedKms(List<Ride> rides) {
        // string -> date (for dto, but time is not important)
        HashMap<String, Double> crossedKmsPerDay = new HashMap<>();
        for(Ride ride: rides) {
            // reports are only for the last month
            if (isDateOlderThanOneMonth(ride.getStartTime())) continue;
            String date = ride.getStartTime().toString().substring(0, 10);
            double kms = iSelectionDriver.getDistance(ride.getLocations().iterator().next().getDeparture(),
                    ride.getLocations().iterator().next().getDestination());
            if(!crossedKmsPerDay.containsKey(date)) crossedKmsPerDay.put(date, kms);
            else crossedKmsPerDay.put(date, crossedKmsPerDay.get(date) + kms);
        }

        return crossedKmsPerDay;
    }

    private HashMap<String, Double> calculateNumberOfRides(List<Ride> rides) {
         HashMap<String, Double> ridesPerDay = new HashMap<>();
         for (Ride ride: rides) {
             if (isDateOlderThanOneMonth(ride.getStartTime())) continue;
             String date = ride.getStartTime().toString().substring(0, 10);
             if(!ridesPerDay.containsKey(date)) ridesPerDay.put(date, 1.0);
             else ridesPerDay.put(date, ridesPerDay.get(date) + 1);
        }

        return ridesPerDay;
    }

    private HashMap<String, Double> calculateSumOfMoney(List<Ride> rides) {
        HashMap<String, Double> sumOfMoneyPerDay = new HashMap<>();

        for (Ride ride: rides) {
            if(isDateOlderThanOneMonth(ride.getStartTime())) continue;
            String date = ride.getStartTime().toString().substring(0, 10);
            if(!sumOfMoneyPerDay.containsKey(date)) sumOfMoneyPerDay.put(date, ride.getTotalCost());
            else sumOfMoneyPerDay.put(date, sumOfMoneyPerDay.get(date) + ride.getTotalCost());
        }

        return sumOfMoneyPerDay;
    }

    // TODO : delete methods below
    @Override
    public void createWeeklyReport() {

    }

    @Override
    public void createMonthlyReport() {

    }

    @Override
    public void createDailyReport() {

    }

    @Override
    public AllDTO<Report> createSumOfMoneyReportPassengers() {
        List<Report> reports = new ArrayList<>();
        for (UserDTO p : iPassengerService.getAll(0,0)){
            reports.addAll(createSumOfMoneyReport(p.getId()).getResults());
        }
        return new AllDTO<>(reports.size(), reports);
    }

    @Override
    public AllDTO<Report> createSumOfMoneyReportDrivers() {
        List<Report> reports = new ArrayList<>();
        for (UserDTO p : iDriverService.getAll().getResults()){
            reports.addAll(createSumOfMoneyReport(p.getId()).getResults());
        }
        return new AllDTO<>(reports.size(), reports);
    }

    @Override
    public AllDTO<Report> createCrossedKmsReportDrivers() {
        List<Report> reports = new ArrayList<>();
        for (UserDTO p : iDriverService.getAll().getResults()){
            reports.addAll(createCrossedKmReport(p.getId()).getResults());
        }
        return new AllDTO<>(reports.size(), reports);
    }

    @Override
    public AllDTO<Report> createCrossedKmsReportPassengers() {
        List<Report> reports = new ArrayList<>();
        for (UserDTO p : iPassengerService.getAll(0,0)){
            reports.addAll(createCrossedKmReport(p.getId()).getResults());
        }
        return new AllDTO<>(reports.size(), reports);
    }
}
