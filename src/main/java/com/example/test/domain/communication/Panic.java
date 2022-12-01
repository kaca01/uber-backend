package com.example.test.domain.communication;

import java.time.LocalDate;

public class Panic {
    private int id;
    private int userId;
    private int rideId;
    private LocalDate time;
    private String reason;

    public Panic() {

    }

    public Panic(int id, int userId, int rideId, LocalDate time, String reason) {
        this.id = id;
        this.userId = userId;
        this.rideId = rideId;
        this.time = time;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Panic{" +
                "id=" + id +
                ", userId=" + userId +
                ", rideId=" + rideId +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                '}';
    }
}
