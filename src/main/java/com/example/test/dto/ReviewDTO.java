package com.example.test.dto;

public class ReviewDTO {
    // TODO add dtos
    private Long id;
    private int rating;
    private String comment;
    // private UserDTO passenger;

    public ReviewDTO() {

    }

    // request
    public ReviewDTO(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }


    // response
    public ReviewDTO(Long id, int rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        // TODO this.passenger = passenger;
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
}
