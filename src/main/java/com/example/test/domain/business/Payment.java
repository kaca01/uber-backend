package com.example.test.domain.business;

import java.time.LocalDate;

public class Payment {
    private int id;
    private int passengerId;
    private LocalDate date;
    private double price;

    public Payment() {
    }

    public Payment(int id, int passengerId, LocalDate date, double price) {
        this.id = id;
        this.passengerId = passengerId;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", passengerId=" + passengerId +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
