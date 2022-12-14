package com.example.test.domain.communication;

import com.example.test.domain.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rejection {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reason", nullable = false)
    private String reason;
    @ManyToOne
    private User user;
    @Column(name = "timeOfRejection", nullable = false)
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
