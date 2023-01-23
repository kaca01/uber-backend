package com.example.test.domain.user;

import com.example.test.enumeration.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ResetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "expiredDate", nullable = false)
    private Date expiredDate;

    @Column(name = "code")
    private String code;

    public ResetPassword(User user, Date expiredDate, String code) {
        this.user = user;
        this.expiredDate = expiredDate;
        this.code = code;
    }
}
