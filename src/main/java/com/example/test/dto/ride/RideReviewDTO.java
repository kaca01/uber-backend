package com.example.test.dto.ride;

import com.example.test.dto.communication.ReviewDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RideReviewDTO {
    private ReviewDTO vehicleReview;
    private ReviewDTO driverReview;

    public RideReviewDTO(ReviewDTO vehicleReview, ReviewDTO driverReview) {
        this.vehicleReview = vehicleReview;
        this.driverReview = driverReview;
    }

}

