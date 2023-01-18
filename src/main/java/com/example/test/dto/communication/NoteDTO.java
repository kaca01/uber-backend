package com.example.test.dto.communication;

import com.example.test.domain.communication.Note;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class NoteDTO {

    private Long id;
    private String date;
    @NotEmpty
    @NotNull
    @Length(max = 250)
    private String message;

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

}
