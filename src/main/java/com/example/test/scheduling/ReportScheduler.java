package com.example.test.scheduling;

import com.example.test.service.interfaces.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class ReportScheduler {

    @Autowired
    IReportService reportService;

    @Scheduled(cron = "0 15 8 1 * * ?")
    public void scheduleMonthlyReport() {

        System.out.println("Generating monthly report");

        // funkcija ce generisati fajl sa odgovarajucim podacima
        reportService.createMonthlyReport();
    }

    @Scheduled(cron = "0 15 8 * * 1 ?")
    public void scheduleWeeklyReport() {

        System.out.println("Generating weekly report");

        // funkcija ce generisati fajl sa odgovarajucim podacima
        reportService.createWeeklyReport();
    }

    @Scheduled(cron = "0 15 8 * * * ?")
    public void scheduleDailyReport() {

        System.out.println("Generating daily report");

        // funkcija ce generisati fajl sa odgovarajucim podacima
        reportService.createDailyReport();
    }
}
