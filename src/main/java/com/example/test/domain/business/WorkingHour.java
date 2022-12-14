package com.example.test.domain.business;


import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkingHour {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start", nullable = false)
    private Date start;
    @Column(name = "end", nullable = false)
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
