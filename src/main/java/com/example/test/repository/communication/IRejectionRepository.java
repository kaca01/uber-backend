package com.example.test.repository.communication;

import com.example.test.domain.communication.Rejection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRejectionRepository extends JpaRepository<Rejection, Long> {
}
