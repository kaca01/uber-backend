package com.example.test.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserActivation {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "life", nullable = false)
    private int life;
}
