package com.example.test.repository.business;

import com.example.test.domain.business.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkingHourRepository extends JpaRepository<WorkingHour, Integer> {

    public WorkingHour findById(Long id);
}
