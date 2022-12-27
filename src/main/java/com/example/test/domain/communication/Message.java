package com.example.test.domain.communication;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.User;
import com.example.test.dto.communication.MessageDTO;
import com.example.test.enumeration.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User sender;
    @OneToOne(fetch = FetchType.EAGER)
    private User receiver;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "timeOfSending", nullable = false)
    private Date timeOfSending;
    @Column(name = "type", nullable = false)
    private MessageType type;
    @ManyToOne(fetch = FetchType.EAGER)
    private Ride ride;



    public Message(MessageDTO messageDTO) {
        this.receiver.setId(messageDTO.getId());
        this.setMessage(messageDTO.getMessage());
        this.setType(MessageType.valueOf(messageDTO.getType().toUpperCase()));
        this.ride.setId(messageDTO.getId());
    }

    public Message(User sender, User receiver, String message, Date timeOfSending, MessageType type, Ride ride) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timeOfSending = timeOfSending;
        this.type = type;
        this.ride = ride;
    }
}
