package com.example.test.domain.communication;

import java.time.LocalDate;

public class RejectionLetter {
    private int id;
    private int rideId;
    private String reason;
    private int userId;
    private LocalDate time;

    public RejectionLetter() {

    }

    public RejectionLetter(int id, int rideId, String reason, int userId, LocalDate time) {
        this.id = id;
        this.rideId = rideId;
        this.reason = reason;
        this.userId = userId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RejectionLetter{" +
                "id=" + id +
                ", rideId=" + rideId +
                ", reason='" + reason + '\'' +
                ", userId=" + userId +
                ", time=" + time +
                '}';
    }
}
