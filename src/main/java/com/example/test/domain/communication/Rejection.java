package com.example.test.domain.communication;

import com.example.test.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Rejection {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reason", nullable = false)
    private String reason;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "timeOfRejection", nullable = false)
    private Date timeOfRejection;

    public Rejection(String reason, User user, Date timeOfRejection) {
        this.reason = reason;
        this.user = user;
        this.timeOfRejection = timeOfRejection;
    }
}
