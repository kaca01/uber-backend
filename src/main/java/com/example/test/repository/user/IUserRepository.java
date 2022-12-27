package com.example.test.repository.user;

import com.example.test.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

    public User findById(Long id);
}
