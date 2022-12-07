package com.example.test.dto;

import com.example.test.domain.communication.Review;
import com.example.test.domain.user.Passenger;

public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private UserDTO passenger;

    public ReviewDTO() {

    }

    public ReviewDTO(Review review) {
        this(review.getId(), review.getRating(), review.getComment(), new UserDTO(review.getPassenger().getId(), review.getPassenger().getEmail()));
    }

    // request
    public ReviewDTO(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }


    // response
    public ReviewDTO(Long id, int rating, String comment, UserDTO passenger) {
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

    public UserDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(UserDTO passenger) {
        this.passenger = passenger;
    }
}
