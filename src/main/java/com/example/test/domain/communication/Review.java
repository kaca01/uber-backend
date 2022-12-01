package com.example.test.domain.communication;

import com.example.test.enumeration.Grade;

public class Review {
    private int id;
    private Grade grade;
    private String comment;
    private int rideId;
    private int passengerId;

    public Review() {

    }

    public Review(int id, Grade grade, String comment, int rideId, int passengerId) {
        this.id = id;
        this.grade = grade;
        this.comment = comment;
        this.rideId = rideId;
        this.passengerId = passengerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                ", rideId=" + rideId +
                ", passengerId=" + passengerId +
                '}';
    }
}
