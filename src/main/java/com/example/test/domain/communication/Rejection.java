package com.example.test.domain.communication;

import com.example.test.domain.user.User;

import java.util.Date;

public class Rejection {
    private Long id;
    private String reason;
    private User user;
    private Date timeOfRejection;

    public Rejection() {

    }

    public Rejection(Long id, String reason, User user, Date timeOfRejection) {
        this.id = id;
        this.reason = reason;
        this.user = user;
        this.timeOfRejection = timeOfRejection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimeOfRejection() {
        return timeOfRejection;
    }

    public void setTimeOfRejection(Date timeOfRejection) {
        this.timeOfRejection = timeOfRejection;
    }

    @Override
    public String toString() {
        return "RejectionLetter{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", user=" + user +
                ", time=" + timeOfRejection +
                '}';
    }
}
