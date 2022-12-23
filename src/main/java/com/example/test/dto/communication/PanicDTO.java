package com.example.test.dto.communication;

import com.example.test.domain.communication.Message;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PanicDTO {

    private String reason;
    private Long id;
    private UserDTO user;
    private RideDTO ride;
    private String time;

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
}
