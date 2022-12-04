package com.example.test.domain.communication;

import com.example.test.domain.user.Passenger;
import com.example.test.enumeration.Grade;
import com.example.test.enumeration.ReviewType;

//todo u specifikaciji receno da korisnik ocijeni vozaca, vozilo i ostavi komentar (tako smo u figmi) i voznja ima listu recenzija
//todo medjutim tako nije u njihovom klasnom dijagramu, dodala sam reviewType (u tom slucaju ce biti dva koemntara-treba promijeniti na figmi)
public class Review {
    private int id;
    private Grade grade;
    private String comment;
    private Passenger passenger;
    private ReviewType type;

    public Review() {

    }

    public Review(int id, Grade grade, String comment, Passenger passenger, ReviewType type) {
        this.id = id;
        this.grade = grade;
        this.comment = comment;
        this.passenger = passenger;
        this.type = type;
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

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ReviewType getType() {
        return type;
    }

    public void setType(ReviewType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                ", passenger=" + passenger +
                ", type=" + type +
                '}';
    }
}
