package com.example.test.dto;

public class LoginDTO {
    private String email;
    private String password;
    private String accessToken;
    private String refreshToken;

    public LoginDTO() {

    }


    // response
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // request
    public LoginDTO(String accessToken, String refreshToken, boolean flag) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
