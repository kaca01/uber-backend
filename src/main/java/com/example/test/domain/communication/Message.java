package com.example.test.domain.communication;

import com.example.test.domain.user.User;
import com.example.test.enumeration.MessageType;

import java.time.LocalDate;

public class Message {
    private int id;
    private User sender;
    private User receiver;
    private String message;
    private LocalDate time;
    private MessageType messageType;

    public Message() {

    }

    public Message(int id, User sender, User receiver, String message, LocalDate time, MessageType messageType) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.time = time;
        this.messageType = messageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", messageType=" + messageType +
                '}';
    }
}
