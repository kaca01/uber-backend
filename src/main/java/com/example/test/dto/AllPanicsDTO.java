package com.example.test.dto;

import java.util.ArrayList;

public class AllPanicsDTO {
    private int totalCount;
    private ArrayList<PanicDTO> results;

    public AllPanicsDTO(){}

    public AllPanicsDTO(int totalCount, ArrayList<PanicDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<PanicDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<PanicDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AllPanicsDTO{" +
                "totalCount=" + totalCount +
                ", results=" + results +
                '}';
    }
}
