package com.example.test.domain.communication;

import com.example.test.domain.user.User;

public class Note {
    private String description;
    private User user;

    public Note()
    {

    }

    public Note(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Note{" +
                "description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
