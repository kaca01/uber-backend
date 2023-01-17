package com.example.test.dto.user;

public class UserTokenState {
    private String accessToken;
    private String refreshToken;

    public UserTokenState() {
        this.accessToken = null;
        this.refreshToken = null;
    }

    public UserTokenState(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
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
