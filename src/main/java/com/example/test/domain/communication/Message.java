package com.example.test.domain.communication;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.enumeration.MessageType;

import java.util.Date;

public class Message {
    private Long id;
    private User sender;
    private User receiver;
    private String message;
    private Date timeOfSending;
    private MessageType type;
    private Ride ride;

    public Message() {

    }

    public Message(Long id, User sender, User receiver, String message, Date timeOfSending, MessageType type, Ride ride) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timeOfSending = timeOfSending;
        this.type = type;
        this.ride = ride;
    }

    public Message(MessageDTO messageDTO) {
        this.receiver.setId(messageDTO.getId());
        this.setMessage(messageDTO.getMessage());
        this.setType(MessageType.valueOf(messageDTO.getType().toUpperCase()));
        this.ride.setId(messageDTO.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeOfSending() {
        return timeOfSending;
    }

    public void setTimeOfSending(Date timeOfSending) {
        this.timeOfSending = timeOfSending;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                ", time=" + timeOfSending +
                ", type=" + type +
                ", ride=" + ride +
                '}';
    }
}
