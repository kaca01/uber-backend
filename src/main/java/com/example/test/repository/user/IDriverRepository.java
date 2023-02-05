package com.example.test.repository.user;

import com.example.test.domain.user.Driver;
import com.example.test.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findById(Long id);

    public Driver findByEmail(String email);

    Driver findByVehicleId(Long id);
    List<Driver> findByActive(Boolean active);

    @Query("select d.vehicle from Driver d where d.id = ?1")
    public Vehicle findVehicleIdByDriverId(Long id);
}
