package com.example.test.repository.user;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserActivationRepository extends JpaRepository<UserActivation, Long> {

    Optional<UserActivation> findByUser_id(Long passengerId);
    //findFirstByUser_idOrderByDateDesc
}
