package com.example.test.service.implementation;

import com.example.test.service.interfaces.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    @Override
    public void createMonthlyReport() {
        // save data to the file
    }

    @Override
    public void createWeeklyReport() {
        // save data to the file
    }

    @Override
    public void createDailyReport() {
        // save data to the file
    }
}
