package com.example.test.domain.vehicle;

import com.example.test.enumeration.VehicleTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VehicleType {
    private Long id;
    private VehicleTypeName name;
    private double pricePerKm;

    public VehicleType(Long id, VehicleTypeName name, double pricePerKm) {
        this.id = id;
        this.name = name;
        this.pricePerKm = pricePerKm;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "id=" + id +
                ", name=" + name +
                ", pricePerKm=" + pricePerKm +
                '}';
    }
}
