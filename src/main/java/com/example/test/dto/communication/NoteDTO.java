package com.example.test.dto.communication;

import com.example.test.domain.communication.Note;

public class NoteDTO {

    private Long id;
    private String date;
    private String message;

    public NoteDTO(){};

    public NoteDTO(Note note)
    {
        this.id=note.getId();
        this.date=note.getDate().toString();
        this.message= note.getMessage();
    }

    //request
    public NoteDTO(String message) { this.message=message; }

    //response
    public NoteDTO(Long id, String date, String message)
    {
        this.id=id;
        this.date=date;
        this.message=message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
