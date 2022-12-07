package com.example.test.dto;

import java.util.ArrayList;

public class AllReviewsDTO {
    private int totalCount;
    private ArrayList<ReviewDTO> reviews;

    public AllReviewsDTO() {

    }

    // response
    public AllReviewsDTO(int totalCount, ArrayList<ReviewDTO> reviews) {
        this.totalCount = totalCount;
        this.reviews = reviews;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
