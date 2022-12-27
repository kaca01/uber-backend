package com.example.test.repository.vehicle;

import com.example.test.domain.vehicle.VehicleType;
import com.example.test.enumeration.VehicleTypeName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

    public VehicleType getByName(VehicleTypeName name);
}
