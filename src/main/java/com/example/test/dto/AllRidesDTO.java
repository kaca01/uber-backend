package com.example.test.dto;

import java.util.ArrayList;

public class AllRidesDTO {

    private int totalCount;
    private ArrayList<RideDTO> results;

    public AllRidesDTO() {

    }

    public AllRidesDTO(int totalCount, ArrayList<RideDTO> results) {
            this.totalCount = totalCount;
            this.results = results;
        }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<RideDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<RideDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AllRidesDTO{" +
                "totalCount=" + totalCount +
                ", results=" + results +
                '}';
    }
}