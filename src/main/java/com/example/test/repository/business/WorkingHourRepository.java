package com.example.test.repository.business;

import com.example.test.domain.business.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkingHourRepository extends JpaRepository<WorkingHour, Integer> {

    public WorkingHour findById(Long id);
}
