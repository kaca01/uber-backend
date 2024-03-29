package com.example.test.controller;

import com.example.test.dto.user.UnregisteredUserDTO;
import com.example.test.service.interfaces.IUnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/unregisteredUser/")
public class UnregisteredUserController {

    @Autowired
    private IUnregisteredUserService service;

    // Getting the assumption about the time and cost of the ride
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnregisteredUserDTO> calculateEstimatedValues(@Valid @RequestBody UnregisteredUserDTO unregisteredUserDTO)
    {
        // the data is not stored in the database, so we can pass the dto object to the service
        List<Integer> estimatedValues = service.getEstimationTimeAndCost(unregisteredUserDTO);

        if(estimatedValues == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new UnregisteredUserDTO(estimatedValues.get(0), estimatedValues.get(1)), HttpStatus.OK);
    }
}
