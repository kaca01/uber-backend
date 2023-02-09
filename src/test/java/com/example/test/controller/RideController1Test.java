package com.example.test.controller;

import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.ride.Location;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideController1Test {

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
    public void testPostFavoriteSuccess() {
        FavoriteOrder favoriteOrder = getFavoriteOrder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(favoriteOrder, headers);
        ResponseEntity<FavoriteOrder> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.POST, httpEntity, FavoriteOrder.class);

        FavoriteOrder actualFavorite = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert actualFavorite != null;
        assertEquals(Optional.of(5L), Optional.ofNullable(actualFavorite.getId()));
        assertEquals(favoriteOrder.getPassenger().getEmail(), actualFavorite.getPassenger().getEmail());
        assertEquals(favoriteOrder.getFavoriteName(), actualFavorite.getFavoriteName());
        assertEquals(favoriteOrder.getLocations(), actualFavorite.getLocations());
        assertEquals(favoriteOrder.getVehicleType(), actualFavorite.getVehicleType());
        assertEquals(favoriteOrder.isBabyTransport(), actualFavorite.isBabyTransport());
        assertEquals(favoriteOrder.isPetTransport(), actualFavorite.isPetTransport());
    }

    @Test
    public void testPostFavoriteOrderForbiddenAdmin() {
        FavoriteOrder favoriteOrder = getFavoriteOrder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(favoriteOrder, headers);
        ResponseEntity<FavoriteOrder> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.POST, httpEntity, FavoriteOrder.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testPostFavoriteOrderForbiddenDriver() {
        FavoriteOrder favoriteOrder = getFavoriteOrder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(favoriteOrder, headers);
        ResponseEntity<FavoriteOrder> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.POST, httpEntity, FavoriteOrder.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetFavoriteOrdersSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<AllDTO<FavoriteOrder>> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<AllDTO<FavoriteOrder>>() {
                });

        AllDTO<FavoriteOrder> orders = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, orders.getResults().size());
    }

    @Test
    public void testGetFavoriteOrdersForbiddenAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<AllDTO<FavoriteOrder>> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<AllDTO<FavoriteOrder>>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetFavoriteOrdersForbiddenDriver() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<AllDTO<FavoriteOrder>> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<AllDTO<FavoriteOrder>>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetFavoriteOrdersUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<AllDTO<FavoriteOrder>> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<AllDTO<FavoriteOrder>>() {
                });

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFavoriteOrderSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites/1", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFavoriteRideNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenPassenger);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites/6", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFavoriteForbiddenAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenAdmin);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites/1", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFavoriteForbiddenDriver() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites/1", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFavoriteUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(RIDE_URL + "/favorites/1", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    private FavoriteOrder getFavoriteOrder() {
        Set<Passenger> passengers = new HashSet<>();
        Passenger passenger = new Passenger();
        passenger.setId(4L);
        passenger.setEmail("sima@gmail.com");
        passengers.add(passenger);
        Passenger passenger2 = new Passenger();
        passenger2.setId(2L);
        passenger2.setEmail("ana@gmail.com");
        Set<Route> locations = new HashSet<>();
        Route route = new Route();
        Location departure = new Location("Djordja Mikeša 2", 19.807387, 45.235866);
        Location destination = new Location("Veselina Maslese 62", 19.809787, 45.259711);
        route.setDeparture(departure);
        route.setDestination(destination);
        route.setId(1L);
        locations.add(route);
        return new FavoriteOrder(5L, "Home to work", VehicleTypeName.STANDARD,
                passenger2, passengers, false, false, locations);
    }

}

