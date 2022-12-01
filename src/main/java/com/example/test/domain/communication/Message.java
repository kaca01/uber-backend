package com.example.test.domain.communication;

import com.example.test.enumeration.MessageType;

import java.time.LocalDate;

public class Message {
    private int id;
    private int senderId;
    private int recipientId;
    private String message;
    private LocalDate time;
    private MessageType messageType;
    private int rideId;

    public Message() {

    }

    public Message(int id, int senderId, int recipientId, String message, LocalDate time, MessageType messageType,
                   int rideId) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
        this.time = time;
        this.messageType = messageType;
        this.rideId = rideId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", messageType=" + messageType +
                ", rideId=" + rideId +
                '}';
    }
}
