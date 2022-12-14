package com.example.test.domain.communication;

import com.example.test.domain.user.User;
import com.example.test.dto.communication.NoteDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "message", nullable = false)
    private String message;
    @ManyToOne
    private User user;

    public Note()
    {

    }

    public Note(Long id, Date date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.user = null;
    }

    public Note(NoteDTO noteDTO) {
        this.setMessage(noteDTO.getMessage());
    }

    public Note(Long id, Date date, String message, User user) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
                "id=" + id +
                ", date=" + date +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
