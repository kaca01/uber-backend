package com.example.test.dto;

import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Passenger;

public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private Passenger passenger;

    public ReviewDTO() {

    }

    public ReviewDTO(Review review) {
        this(review.getId(), review.getRating(), review.getComment(), review.getPassenger());
    }

    // request
    public ReviewDTO(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }


    // response
    public ReviewDTO(Long id, int rating, String comment, Passenger passenger) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
