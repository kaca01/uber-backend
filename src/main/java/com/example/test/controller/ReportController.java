package com.example.test.controller;

import com.example.test.domain.business.Report;
import com.example.test.dto.AllDTO;
import com.example.test.service.interfaces.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    IReportService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/crossed-kms/{id}")
    public ResponseEntity<AllDTO<Report>> getCrossedKmsReport(@PathVariable Long id){
        AllDTO<Report> reports = service.createCrossedKmReport(id);
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/num-of-rides/{id}")
    public ResponseEntity<AllDTO<Report>> getNumOfRidesReport(@PathVariable Long id){
        AllDTO<Report> reports = service.createNumOfRidesReport(id);
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/money-sum/{id}")
    public ResponseEntity<AllDTO<Report>> getSumOfMoneyReport(@PathVariable Long id){
        AllDTO<Report> reports = service.createSumOfMoneyReport(id);
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/money-sum/drivers")
    public ResponseEntity<AllDTO<Report>> getSumOfMoneyReportDrivers(){
        AllDTO<Report> reports = service.createSumOfMoneyReportDrivers();
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/money-sum/passengers")
    public ResponseEntity<AllDTO<Report>> getSumOfMoneyReportPassengers(){
        AllDTO<Report> reports = service.createSumOfMoneyReportDrivers();
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/crossed-kms/drivers")
    public ResponseEntity<AllDTO<Report>> createCrossedKmsReportDrivers(){
        AllDTO<Report> reports = service.createCrossedKmsReportDrivers();
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @GetMapping("/crossed-kms/passengers")
    public ResponseEntity<AllDTO<Report>> createCrossedKmsReportPassengers(){
        AllDTO<Report> reports = service.createCrossedKmsReportPassengers();
        return new ResponseEntity<AllDTO<Report>>(reports, HttpStatus.OK);
    }
}
