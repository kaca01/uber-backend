package com.example.test.domain.user;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserActivation {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "life", nullable = false)
    private int life;

    public UserActivation()
    {

    }

    public UserActivation(Long id, User user, LocalDate date, int life) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.life = life;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
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
