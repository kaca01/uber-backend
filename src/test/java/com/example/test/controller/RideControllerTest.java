package com.example.test.controller;

import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.LoginDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String PASSENGER_EMAIL = "ana@gmail.com";
    private Long PASSENGER_ID = 2L;
    private String DRIVER_EMAIL = "boki@gmail.com";
    private Long DRIVER_ID = 6L;
    private String ADMIN_EMAIL = "darko@gmail.com";
    private Long ADMIN_ID = 11L;
    private String PASSWORD = "123";

    private String RIDE_URL = "/api/ride";

    private String accessTokenPassenger;
    private String accessTokenDriver;
    private String accessTokenAdmin;

    @Before
    public void login() throws JSONException {
        ResponseEntity<String> responseEntity1 =
                restTemplate.postForEntity("/api/user/login",
                        new LoginDTO(PASSENGER_EMAIL, PASSWORD),
                        String.class);
        JSONObject json1 = new JSONObject(responseEntity1.getBody());
        accessTokenPassenger = json1.getString("accessToken");
        System.out.println(accessTokenPassenger);

        ResponseEntity<String> responseEntity2 =
                restTemplate.postForEntity("/api/user/login",
                        new LoginDTO(DRIVER_EMAIL, PASSWORD),
                        String.class);
        JSONObject json2 = new JSONObject(responseEntity2.getBody());
        accessTokenDriver = json2.getString("accessToken");
        System.out.println(accessTokenDriver);

        ResponseEntity<String> responseEntity3 =
                restTemplate.postForEntity("/api/user/login",
                        new LoginDTO(ADMIN_EMAIL, PASSWORD),
                        String.class);
        JSONObject json3 = new JSONObject(responseEntity3.getBody());
        accessTokenAdmin = json3.getString("accessToken");
        System.out.println(accessTokenAdmin);
    }

    @Test
    public void testGetRideDetailsSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/6", HttpMethod.GET, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        RideDTO excpectedRide = getActiveRide();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(ride).isEqualTo(excpectedRide);
    }

    @Test
    public void testGetRideDetailsUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/6", HttpMethod.GET, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPassengerActiveRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/4/active", HttpMethod.GET, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        RideDTO expectedRide = getActiveRide();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(ride).isEqualTo(expectedRide);
    }

    @Test
    public void testGetPassengerActiveRideForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/4/active", HttpMethod.GET, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPassengerActiveRideUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/4/active", HttpMethod.GET, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    private RideDTO getActiveRide(){
        List<UserDTO> passengers = new ArrayList<>();
        passengers.add(new UserDTO(4L, "sima@gmail.com"));
        Set<Route> locations = new HashSet<>();
        Route route = new Route();
        Location departure = new Location("Djordja Mikeša 2", 19.807387, 45.235866);
        Location destination = new Location("Veselina Maslese 62", 19.809787, 45.259711);
        route.setDeparture(departure);
        route.setDestination(destination);
        route.setId(1L);
        locations.add(route);
        UserDTO driver = new UserDTO(6L, "Boris", "Petrov", "U3dhZ2dlciByb2Nrcw==", "+381123123", "boki@gmail.com", "Sarajevska 2"
                , null, false, true, null);
        return new RideDTO(6L, "2022-12-22T14:42:24.893Z", null, 350,
                locations, passengers, VehicleTypeName.STANDARD.toString(),false, false,
                7, "ACTIVE", driver, null,"2023-02-06T22:47:24.893Z");
    }
}
