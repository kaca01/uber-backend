package com.example.test.repository.communication;

import com.example.test.domain.communication.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Long> {

}
