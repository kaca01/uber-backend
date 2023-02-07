package com.example.test.service.interfaces;

import com.example.test.domain.business.Report;
import com.example.test.dto.AllDTO;

public interface IReportService {

    public AllDTO<Report> createCrossedKmReport(Long userId);

    public AllDTO<Report> createNumOfRidesReport(Long userId);

    public AllDTO<Report> createSumOfMoneyReport(Long userId);

    // TODO : delete methods below

    public void createWeeklyReport();

    public void createMonthlyReport();

    public void createDailyReport();

    AllDTO<Report> createSumOfMoneyReportPassengers();

    AllDTO<Report> createSumOfMoneyReportDrivers();

    AllDTO<Report> createCrossedKmsReportDrivers();

    AllDTO<Report> createCrossedKmsReportPassengers();
}
