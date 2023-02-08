package com.example.test.controller;

import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.LoginDTO;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String PASSENGER_EMAIL = "ana@gmail.com";
    private String DRIVER_EMAIL = "boki@gmail.com";
    private String ADMIN_EMAIL = "darko@gmail.com";
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
    public void testGetRide() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenDriver);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RideDTO> responseEntity =
                restTemplate.exchange(RIDE_URL + "/5", HttpMethod.GET, httpEntity, RideDTO.class);

        RideDTO ride = responseEntity.getBody();
        System.out.println(ride);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
