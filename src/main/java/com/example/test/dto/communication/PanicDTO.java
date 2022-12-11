package com.example.test.dto.communication;

import com.example.test.domain.communication.Message;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;

public class PanicDTO {

    private String reason;
    private Long id;
    private UserDTO user;
    private RideDTO ride;
    private String time;

    public PanicDTO(){}

    //request
    public PanicDTO(String reason) {
        this.reason = reason;
    }

    public PanicDTO(Message message)
    {
        this.id = message.getId();
        this.user = new UserDTO(message.getSender());
        this.ride = new RideDTO(message.getRide());
        this.time = message.getTimeOfSending().toString();
        this.reason = message.getMessage();
    }

    //response
    public PanicDTO(String reason, Long id, UserDTO user, RideDTO ride, String time) {
        this.reason = reason;
        this.id = id;
        this.user = user;
        this.ride = ride;
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RideDTO getRide() {
        return ride;
    }

    public void setRide(RideDTO ride) {
        this.ride = ride;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PanicDTO{" +
                "reason='" + reason + '\'' +
                ", id=" + id +
                ", user=" + user +
                ", ride=" + ride +
                ", time='" + time + '\'' +
                '}';
    }
}
