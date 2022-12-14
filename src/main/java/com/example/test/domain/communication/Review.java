package com.example.test.domain.communication;

import com.example.test.domain.user.Passenger;
import com.example.test.dto.communication.ReviewDTO;
import com.example.test.enumeration.ReviewType;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rating", nullable = false)
    private int rating;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    private Passenger passenger;
    @Column(name = "type", nullable = false)
    private ReviewType type;


    public Review() {

    }

    public Review(Long id, int rating, String comment, Passenger passenger, ReviewType type) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
        this.type = type;
    }

    public Review(ReviewDTO reviewDTO) {
        this.setRating(reviewDTO.getRating());
        this.setComment(reviewDTO.getComment());
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

    public ReviewType getType() {
        return type;
    }

    public void setType(ReviewType type) {
        this.type = type;
    }

    public Long getPassengerId() {
        return passenger.getId();
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", passenger=" + passenger +
                ", type=" + type +
                '}';
    }
}
