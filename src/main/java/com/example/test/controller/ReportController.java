package com.example.test.controller;

import com.example.test.domain.business.Report;
import com.example.test.dto.AllDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.service.interfaces.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    IReportService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AllDTO<Report>> getReports(@PathVariable Long id){

        return new ResponseEntity<AllDTO<Report>>(HttpStatus.OK);
    }
}
