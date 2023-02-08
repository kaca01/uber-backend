package com.example.test.controller;

import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.LoginDTO;
import com.example.test.dto.user.UserDTO;
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
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","6");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}", HttpMethod.GET, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        RideDTO expectedRide = getActiveRide();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(ride).isEqualTo(expectedRide);
    }

    @Test
    public void testGetRideDetailsUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","6");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}", HttpMethod.GET, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPassengerActiveRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","15");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/{id}/active", HttpMethod.GET, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        RideDTO expectedRide = getActiveRide();
        List<UserDTO> passengers = new ArrayList<>();
        passengers.add(new UserDTO(15L, "milojka@gmail.com"));
        expectedRide.setPassengers(passengers);
        expectedRide.setDriver(new UserDTO(14L, "Smiljana", "Smiljić", "U3dhZ2dlciByb2Nrcw==",
                "+381123123", "smilja@gmail.com", "Zarka Zrenjanina 18"
                , null, false, true, null));
        expectedRide.setId(10L);
        Set<Route> locations = new HashSet<>();
        Route route = new Route();
        Location departure = new Location("Gajeva 2", 19.84799, 45.223481);
        Location destination = new Location("Boska Buhe 10A", 19.844632, 45.242509);
        route.setDeparture(departure);
        route.setDestination(destination);
        route.setId(7L);
        locations.add(route);
        expectedRide.setLocations(locations);
        ride.setRejection(null);
        expectedRide.setScheduledTime("2023-01-13T17:20:24.893Z");
        expectedRide.getDriver().setChanged(false);
        expectedRide.getDriver().setTelephoneNumber("+381009333");
        expectedRide.setVehicleType("VAN");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(ride).isEqualTo(expectedRide);
    }

    @Test
    public void testGetPassengerActiveRideForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","4");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/{id}/active", HttpMethod.GET, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPassengerActiveRideUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","4");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/{id}/active", HttpMethod.GET, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testPassengerCancelRideSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","9");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}/withdraw", HttpMethod.PUT, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        RideDTO expectedRide = getActiveRide();
        expectedRide.setId(9L);
        List<UserDTO> passengers = new ArrayList<>();
        passengers.add(new UserDTO(12L, "milan@gmail.com"));
        expectedRide.setPassengers(passengers);
        expectedRide.setStatus("REJECTED");
        ride.setRejection(null);
        expectedRide.setScheduledTime("2023-01-13T17:20:24.893Z");
        UserDTO driver = new UserDTO(6L, "Boris", "Petrov", "U3dhZ2dlciByb2Nrcw==", "+381123123", "boki@gmail.com", "Sarajevska 2"
                , null, false, true, null);
        ride.setDriver(driver);
        expectedRide.setVehicleType("VAN");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(ride).isEqualTo(expectedRide);
    }

    @Test
    public void testPassengerCancelRideForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","1");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}/withdraw", HttpMethod.PUT, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testPanicSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        PanicDTO expectedPanic = new PanicDTO("Panic message");

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(expectedPanic, headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","6");

        ResponseEntity<PanicDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}/panic", HttpMethod.PUT, httpEntity, PanicDTO.class, param);

        PanicDTO realPanic = responseEntity.getBody();
        System.out.println(realPanic);

        RideDTO expectedRide = getActiveRide();
        expectedRide.setPanic(true);
        expectedPanic.setRide(expectedRide);
        expectedPanic.setId(10L);
        UserDTO user = expectedRide.getDriver();
        user.setChanged(false);
        expectedPanic.setUser(user);
        expectedPanic.setTime(realPanic.getTime());
        realPanic.getRide().getDriver().setChanged(false);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(realPanic).isEqualTo(expectedPanic);
    }

    @Test
    public void testPanicForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","6");

        ResponseEntity<PanicDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}/withdraw", HttpMethod.PUT, httpEntity, PanicDTO.class, param);

        PanicDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testStartSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","1");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}/start", HttpMethod.PUT, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        RideDTO expectedRide = getActiveRide();
        expectedRide.setStatus("ACTIVE");
        expectedRide.setId(1L);
        expectedRide.setStartTime(ride.getStartTime());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(ride).isEqualTo(expectedRide);
    }

    @Test
    public void testStartForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id","1");

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/{id}/start", HttpMethod.PUT, httpEntity, RideDTO.class, param);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
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
