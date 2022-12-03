package com.example.test.domain.business;

import com.example.test.domain.user.Passenger;

import java.time.LocalDate;

public class Payment {
    private int id;
    private Passenger passenger;
    private LocalDate date;
    private double price;

    public Payment() {
    }

    public Payment(int id, Passenger passenger, LocalDate date, double price) {
        this.id = id;
        this.passenger = passenger;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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
                ", passenger=" + passenger +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
