package com.example.test.dto;

import com.example.test.domain.business.WorkingHour;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkingHourDTO {

    private Long id;
    private String start;
    private String end;

    public WorkingHourDTO() {

    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "WorkingHourDTO{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
