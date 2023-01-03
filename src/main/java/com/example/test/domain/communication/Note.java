package com.example.test.domain.communication;

import com.example.test.domain.user.User;
import com.example.test.dto.communication.NoteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "message", nullable = false)
    private String message;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Note(Long id, Date date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.user = null;
    }

    public Note(NoteDTO noteDTO) throws ParseException {
        this.setMessage(noteDTO.getMessage());
        this.setDate(new Date());
    }

}
