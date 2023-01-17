package com.example.test.repository.user;

import com.example.test.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findById(Long id);
}
