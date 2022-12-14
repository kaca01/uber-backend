package com.example.test.domain.vehicle;

import com.example.test.enumeration.VehicleTypeName;

import javax.persistence.*;

@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private VehicleTypeName name;
    @Column(name = "pricePerKm", nullable = false)
    private double pricePerKm;

    public VehicleType() {

    }

    public VehicleType(Long id, VehicleTypeName name, double pricePerKm) {
        this.id = id;
        this.name = name;
        this.pricePerKm = pricePerKm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleTypeName getName() {
        return name;
    }

    public void setName(VehicleTypeName name) {
        this.name = name;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
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
