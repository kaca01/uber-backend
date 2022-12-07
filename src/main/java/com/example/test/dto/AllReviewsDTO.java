package com.example.test.dto;

import java.util.ArrayList;

public class AllReviewsDTO {
    private int totalCount;
    private ArrayList<ReviewDTO> results;

    public AllReviewsDTO() {

    }

    // response
    public AllReviewsDTO(int totalCount, ArrayList<ReviewDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<ReviewDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<ReviewDTO> results) {
        this.results = results;
    }
}
