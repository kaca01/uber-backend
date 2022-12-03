package com.example.test.domain.communication;

import com.example.test.domain.user.User;

import java.time.LocalDate;

public class RejectionLetter {
    private int id;
    private String reason;
    private User user;
    private LocalDate time;

    public RejectionLetter() {

    }

    public RejectionLetter(int id, String reason, User user, LocalDate time) {
        this.id = id;
        this.reason = reason;
        this.user = user;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
                ", reason='" + reason + '\'' +
                ", user=" + user +
                ", time=" + time +
                '}';
    }
}
