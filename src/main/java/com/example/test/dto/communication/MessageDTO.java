package com.example.test.dto.communication;


import com.example.test.domain.communication.Message;

public class MessageDTO {

    private Long id;
    private String timeOfSending;
    private Long senderId;
    private Long receiverId;
    private String message;
    private String type;
    private Long rideId;

    public MessageDTO()
    {

    }

    public MessageDTO(Message message)
    {
        this.id = message.getId();
        this.timeOfSending = message.getTimeOfSending().toString();
        this.senderId = message.getSender().getId();
        this.receiverId = message.getReceiver().getId();
        this.message = message.getMessage();
        this.type = message.getType().toString();
        this.rideId = message.getRide().getId();
    }

    //request
    public MessageDTO(Long receiverId, String message, String type, Long rideId) {
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }

    //response
    public MessageDTO(Long id, String timeOfSending, Long senderId, Long receiverId, String message,
                      String type, Long rideId) {
        this.id = id;
        this.timeOfSending = timeOfSending;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeOfSending() {
        return timeOfSending;
    }

    public void setTimeOfSending(String timeOfSending) {
        this.timeOfSending = timeOfSending;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", timeOfSending='" + timeOfSending + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", rideId=" + rideId +
                '}';
    }
}
