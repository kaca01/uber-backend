package com.example.test.repository.vehicle;

import com.example.test.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

}
