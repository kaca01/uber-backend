package com.example.test.dto;

import com.example.test.domain.business.WorkingHour;

import java.util.ArrayList;

public class AllWorkingHoursDTO {

    private int totalCount;
    private ArrayList<WorkingHourDTO> results;

    public AllWorkingHoursDTO() {
    }

    // response
    public AllWorkingHoursDTO(int totalCount, ArrayList<WorkingHourDTO> results) {
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

    public ArrayList<WorkingHourDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<WorkingHourDTO> results) {
        this.results = results;
    }
}
