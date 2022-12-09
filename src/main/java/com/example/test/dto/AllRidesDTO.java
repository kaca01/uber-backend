package com.example.test.dto;

import java.util.ArrayList;
import java.util.List;

public class AllRidesDTO {

    private int totalCount;
    private List<RideDTO> results;

    public AllRidesDTO() {

    }

    public AllRidesDTO(int totalCount, List<RideDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<RideDTO> getResults() {
        return results;
    }

    public void setResults(List<RideDTO> results) {
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