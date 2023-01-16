package com.example.test.dto.communication;

import com.example.test.domain.communication.Review;
import com.example.test.dto.user.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class ReviewDTO {
    private Long id;
    @Min(1)
    @Max(10)
    @NotNull
    private int rating;
    @Length(max = 500)
    @NotEmpty
    private String comment;
    private UserDTO passenger;

    public ReviewDTO(Review review) {
        this(review.getId(), review.getRating(), review.getComment(),
                new UserDTO(review.getPassenger().getId(), review.getPassenger().getEmail()));
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
}
