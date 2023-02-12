package com.example.test.repository.account;

import com.example.test.domain.account.UserChanges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserChangesRepository extends JpaRepository<UserChanges, Long> {

    UserChanges findByDriverId(Long id);
    void deleteAllByDriverId(Long id);
}
