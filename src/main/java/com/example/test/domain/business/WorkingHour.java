package com.example.test.domain.business;


import java.util.Date;

public class WorkingHour {
    private Long id;
    private Date start;
    private Date end;

    public WorkingHour() {

    }

    public WorkingHour(Long id, Date start, Date end) {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


    @Override
    public String toString() {
        return "WorkingHours{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
