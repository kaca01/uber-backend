package com.example.test.repository.user;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserActivationRepository extends JpaRepository<UserActivation, Long> {

    public UserActivation findByUser_id(Long passengerId);
    //findFirstByUser_idOrderByDateDesc
}
