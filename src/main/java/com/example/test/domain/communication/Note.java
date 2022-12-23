package com.example.test.domain.communication;

import com.example.test.domain.user.User;
import com.example.test.dto.communication.NoteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Note {
    private Long id;
    private Date date;
    private String message;
    private User user;

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
