package com.example.test.repository.user;
import com.example.test.domain.user.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPassengerRepository extends JpaRepository<Passenger, Long> {

    public List<Passenger> findAllByActiveIsTrue();


}
