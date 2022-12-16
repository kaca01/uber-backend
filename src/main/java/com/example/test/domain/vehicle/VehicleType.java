package com.example.test.domain.vehicle;

import com.example.test.enumeration.VehicleTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private VehicleTypeName name;
    @Column(name = "pricePerKm", nullable = false)
    private double pricePerKm;
}
