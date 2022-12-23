package com.example.test.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserActivation {
    private Long id;
    private User user;
    private LocalDate date;
    private int life;

    public UserActivation(Long id, User user, LocalDate date, int life) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.life = life;
    }

    @Override
    public String toString() {
        return "UserActivation{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", life=" + life +
                '}';
    }
}
