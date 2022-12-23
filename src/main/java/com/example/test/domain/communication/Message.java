package com.example.test.domain.communication;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.enumeration.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private Long id;
    private User sender;
    private User receiver;
    private String message;
    private Date timeOfSending;
    private MessageType type;
    private Ride ride;

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
