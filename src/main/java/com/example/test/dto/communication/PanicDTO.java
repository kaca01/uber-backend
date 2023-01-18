package com.example.test.dto.communication;

import com.example.test.domain.communication.Message;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;

@NoArgsConstructor
@Data
public class PanicDTO {

    private Long id;
    @Length(max = 500)
    private String reason;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if (message.getTimeOfSending() != null) this.time = format.format(message.getTimeOfSending());
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
