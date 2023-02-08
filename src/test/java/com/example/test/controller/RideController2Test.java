package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.LoginDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.VehicleTypeName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideController2Test {

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
    private String accessTokenPassenger2;
    private String accessTokenDriver;
    private String accessTokenDriver2;
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

        ResponseEntity<String> responseEntity4 =
                restTemplate.postForEntity("/api/user/login",
                        new LoginDTO("sima@gmail.com", PASSWORD),
                        String.class);
        JSONObject json4 = new JSONObject(responseEntity4.getBody());
        accessTokenPassenger2 = json4.getString("accessToken");
        System.out.println(accessTokenPassenger2);

        ResponseEntity<String> responseEntity5 =
                restTemplate.postForEntity("/api/user/login",
                        new LoginDTO("danka@gmail.com", PASSWORD),
                        String.class);
        JSONObject json5 = new JSONObject(responseEntity2.getBody());
        accessTokenDriver2 = json5.getString("accessToken");
        System.out.println(accessTokenDriver2);
    }

    @Test
    public void testGetDriverActiveRideNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/driver/5/active", HttpMethod.GET,
                                      httpEntity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetDriverActiveRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/driver/6/active", HttpMethod.GET,
                        httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
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
        RideDTO excpectedRide = new RideDTO(6L, "2022-12-22T14:42:24.893Z", null, 350,
                locations, passengers, VehicleTypeName.STANDARD.toString(),false, false,
                7, "ACTIVE", driver, null,"2023-02-06T22:47:24.893Z");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ride, excpectedRide);
    }

    @Test
    public void testPostRideSuccess() {
        RideDTO ride = getRide();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(ride, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL, HttpMethod.POST,
                        httpEntity, RideDTO.class);

        RideDTO actualRide = responseEntity.getBody();
        ride.setId(11L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ride.getId(), actualRide.getId());
        assertEquals(ride.getScheduledTime(), actualRide.getScheduledTime());
        assertEquals("PENDING", actualRide.getStatus());
        assertEquals(ride.getPassengers(), actualRide.getPassengers());
        assertEquals(ride.isPetTransport(), actualRide.isPetTransport());
        assertEquals(ride.isBabyTransport(), actualRide.isBabyTransport());
    }

    @Test
    public void testPostRideBadRequest() {
        Location departure = new Location();
        departure.setLatitude(45.26);
        departure.setLongitude(19.83);
        departure.setAddress("Bulevar oslobodjenja 46");
        RideDTO ride = new RideDTO();
        List<UserDTO> passengers = new ArrayList<>();
        passengers.add(new UserDTO(4L, "sima@gmail.com"));
        ride.setPassengers(passengers);
        ride.setVehicleType("STANDARD");
        ride.setBabyTransport(true);
        ride.setPetTransport(true);
        ride.setScheduledTime("2023-01-11T17:45:00.456Z");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(ride, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL, HttpMethod.POST,
                        httpEntity, RideDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testPostRideBadRequestPending() {
        RideDTO ride = getRide();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(ride, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL, HttpMethod.POST,
                        httpEntity, RideDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testPostRideForbidden() {
        RideDTO ride = getRide();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(ride, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL, HttpMethod.POST,
                        httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testAcceptRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/7/accept", HttpMethod.PUT, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert ride != null;
        assertEquals(Optional.ofNullable(ride.getId()), Optional.of(7L));
        assertEquals(null, ride.getStartTime());
        assertEquals(null, ride.getEndTime());
        assertEquals("ACCEPTED", ride.getStatus());
    }

    @Test
    // this driver does not have any pending rides
    public void testAcceptRideBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/5/accept", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testAcceptRideForbiddenPassenger() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/4/accept", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testAcceptRideForbiddenAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/4/accept", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testEndRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver2);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/6/end", HttpMethod.PUT, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert ride != null;
        assertEquals(Optional.ofNullable(ride.getId()), Optional.of(6L));
        assertEquals("2022-12-22T14:42:24.893Z", ride.getStartTime());
        assertNotEquals(null, ride.getEndTime());
        assertEquals("FINISHED", ride.getStatus());
    }

    @Test
    public void testEndRideForbiddenAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/6/end", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testEndRideForbiddenPassenger() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/6/end", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    // driver does not have any active ride
    public void testEndRideBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/5/end", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCancelActiveRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        RejectionDTO rejection = new RejectionDTO();
        rejection.setReason("Some reason");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(rejection, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/1/cancel", HttpMethod.PUT, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert ride != null;
        assertEquals(Optional.ofNullable(ride.getId()), Optional.of(1L));
        assertNotEquals(null, ride.getStartTime());
        assertEquals(null, ride.getEndTime());
        assertEquals("REJECTED", ride.getStatus());
    }

    @Test
    public void testCancelPendingRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver2);
        RejectionDTO rejection = new RejectionDTO();
        rejection.setReason("Some reason");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(rejection, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/4/cancel", HttpMethod.PUT, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert ride != null;
        assertEquals(Optional.ofNullable(ride.getId()), Optional.of(4L));
        assertEquals(null, ride.getStartTime());
        assertEquals(null, ride.getEndTime());
        assertEquals("REJECTED", ride.getStatus());
    }

    @Test
    public void testCancelRideForbiddenPassenger() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        RejectionDTO rejection = new RejectionDTO();
        rejection.setReason("Some reason");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(rejection, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/1/cancel", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testCancelRideForbiddenAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        RejectionDTO rejection = new RejectionDTO();
        rejection.setReason("Some reason");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(rejection, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/1/cancel", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testCancelRideBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        RejectionDTO rejection = new RejectionDTO();
        rejection.setReason("Some reason");
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(rejection, headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/2/cancel", HttpMethod.PUT, httpEntity, RideDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    private RideDTO getRide() {
        Location departure = new Location();
        departure.setLatitude(45.26);
        departure.setLongitude(19.83);
        departure.setAddress("Bulevar oslobodjenja 46");
        Route route = new Route();
        route.setDeparture(departure);
        route.setDestination(departure);
        RideDTO ride = new RideDTO();
        Set<Route> locations = new HashSet<>();
        locations.add(route);
        ride.setLocations(locations);
        List<UserDTO> passengers = new ArrayList<>();
        passengers.add(new UserDTO(4L, "sima@gmail.com"));
        ride.setPassengers(passengers);
        ride.setVehicleType("STANDARD");
        ride.setBabyTransport(true);
        ride.setPetTransport(true);
        ride.setScheduledTime("2023-01-11T17:45:00.456Z");

        return ride;
    }
}
