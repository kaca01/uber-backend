package com.example.test.domain.business;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class WorkingHour {
    private Long id;
    private Date start;
    private Date end;

    public WorkingHour(Long id, Date start, Date end) {
        this.id = id;
        this.start = start;
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
