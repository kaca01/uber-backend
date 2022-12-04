package com.example.test.domain.user;

import java.time.LocalDate;

public class UserActivation {
    private int id;
    private User user;
    private LocalDate date;
    private int life;

    public UserActivation()
    {

    }

    public UserActivation(int id, User user, LocalDate date, int life) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
