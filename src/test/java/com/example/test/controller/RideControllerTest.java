package com.example.test.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.ride.RideDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.repository.user.IUserRepository;
import com.example.test.service.implementation.RideService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RideController.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(
//        locations = "classpath:application-test.properties")
//@ActiveProfiles("test")
public class RideControllerTest {

    @MockBean
    private RideService rideService;
    @MockBean
    private IPassengerRepository passengerRepository;
    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "ana@gmail.com")
    @Test
    @DisplayName("Creating a ride")
    public void shouldCreateRide() throws Exception {
        RideDTO ride1 = new RideDTO(123L, "2023-01-12T16:20:24.893Z", "2023-01-12T16:40:24.893Z",
                550.0, null, null, "STANDARD", false, false,
                5, "PENDING", null, null, "2023-01-12T16:40:24.893Z");

        Mockito.when(rideService.findOne(123L)).thenReturn(ride1);

        mockMvc.perform(get("/api/ride/{id}", 123L))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }


    @Test
    public void orderRide() throws URISyntaxException {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<>(), RideStatus.PENDING, null, false, false,
                new HashSet<>(), null, new Date());


    }


}
