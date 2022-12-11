package com.example.test.dto.communication;

import java.util.List;

public class AllReviewsDTO {
    private int totalCount;
    private List<ReviewDTO> results;

    public AllReviewsDTO() {

    }

    // response
    public AllReviewsDTO(int totalCount, List<ReviewDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ReviewDTO> getResults() {
        return results;
    }

    public void setResults(List<ReviewDTO> results) {
        this.results = results;
    }
}
