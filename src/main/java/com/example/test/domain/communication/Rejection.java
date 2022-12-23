package com.example.test.domain.communication;

import com.example.test.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Rejection {
    private Long id;
    private String reason;
    private User user;
    private Date timeOfRejection;

    public Rejection(Long id, String reason, User user, Date timeOfRejection) {
        this.id = id;
        this.reason = reason;
        this.user = user;
        this.timeOfRejection = timeOfRejection;
    }

    @Override
    public String toString() {
        return "RejectionLetter{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", user=" + user +
                ", time=" + timeOfRejection +
                '}';
    }
}
