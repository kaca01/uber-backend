package com.example.test.repository.communication;

import com.example.test.domain.communication.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Long> {
}
