package com.example.test.domain.communication;

import com.example.test.domain.user.Passenger;
import com.example.test.dto.communication.ReviewDTO;
import com.example.test.enumeration.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rating", nullable = false)
    private int rating;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    private Passenger passenger;
    @Column(name = "type", nullable = false)
    private ReviewType type;

    public Review(ReviewDTO reviewDTO) {
        this.setRating(reviewDTO.getRating());
        this.setComment(reviewDTO.getComment());
    }

    public Long getPassengerId() {
        return this.getPassenger().getId();
    }
}
