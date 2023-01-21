package com.example.test.dto.communication;


import com.example.test.domain.communication.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class MessageDTO {

    private Long id;
    private String timeOfSending;
    private Long senderId;
    private Long receiverId;
    @NotEmpty
    @NotNull
    private String message;
    @NotEmpty
    @NotNull
    private String type;
    private Long rideId;

    public MessageDTO(Message message)
    {
        this.id = message.getId();
        this.timeOfSending = message.getTimeOfSending().toString();
        this.senderId = message.getSender().getId();
        if(message.getReceiver()!= null)this.receiverId = message.getReceiver().getId();
        this.message = message.getMessage();
        this.type = message.getType().toString();
        if(message.getRide()!=null)this.rideId = message.getRide().getId();
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

}
