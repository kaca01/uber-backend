package com.example.test.dto;

import com.example.test.domain.business.WorkingHour;

import java.util.ArrayList;

public class WorkingHoursDTO {

    private int totalCount;
    private ArrayList<WorkingHour> results;

    public WorkingHoursDTO() {
    }

    // response
    public WorkingHoursDTO(int totalCount, ArrayList<WorkingHour> results) {
        super();
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<WorkingHour> getResults() {
        return results;
    }

    public void setResults(ArrayList<WorkingHour> results) {
        this.results = results;
    }
}
