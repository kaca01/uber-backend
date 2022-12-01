package com.example.test.domain.business;

import java.time.LocalDate;

public class WorkTime {
    private int id;
    private LocalDate start;
    private LocalDate end;
    private int driverInt;

    public WorkTime() {

    }

    public WorkTime(int id, LocalDate start, LocalDate end, int driverInt) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.driverInt = driverInt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public int getDriverInt() {
        return driverInt;
    }

    public void setDriverInt(int driverInt) {
        this.driverInt = driverInt;
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "start=" + start +
                ", end=" + end +
                ", driverInt=" + driverInt +
                '}';
    }
}
