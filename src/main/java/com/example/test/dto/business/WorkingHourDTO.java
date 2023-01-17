package com.example.test.dto.business;

import com.example.test.domain.business.WorkingHour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Data
public class WorkingHourDTO {

    private Long id;
    @NotNull
    @NotEmpty
    private String start;
    private String end;


    public WorkingHourDTO(WorkingHour wh) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.id = wh.getId();
        this.start = format.format(wh.getStart());
        this.end = format.format(wh.getEnd());
    }

    //response and request
    public WorkingHourDTO(Long id, String start, String end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}
