package com.example.test.service;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.ride.RideDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.service.implementation.RideService;
import com.example.test.service.interfaces.IRideService;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class RideServiceTest {


    @Mock
    IRideRepository rideRepository;

    @Autowired
    @InjectMocks
    RideService rideService;

    @Test
    @DisplayName("Should find ride by id")
    public void shouldFindRideById() {

        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), null, new Date());

        RideDTO expectedRide = new RideDTO(ride);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        RideDTO actualRide = rideService.findOne(123L);

        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());
    }

    @Test
    @DisplayName("Should not find ride by id. Also should throw exception Not Found")
    public void shouldNotFindRideById() {
        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findOne(123L));

        verify(rideRepository, times(1)).findById(123L);
    }
}
