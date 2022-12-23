package com.example.test.domain.communication;

import com.example.test.domain.user.Passenger;
import com.example.test.dto.communication.ReviewDTO;
import com.example.test.enumeration.ReviewType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Review {
    private Long id;
    private int rating;
    private String comment;
    private Passenger passenger;
    private ReviewType type;

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
