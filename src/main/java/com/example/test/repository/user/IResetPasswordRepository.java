package com.example.test.repository.user;

import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.ResetPassword;
import com.example.test.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResetPasswordRepository extends JpaRepository<ResetPassword, Long> {

    ResetPassword findResetPasswordByUserId(Long id);
}
