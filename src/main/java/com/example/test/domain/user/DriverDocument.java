package com.example.test.domain.user;

//todo nemam pojma sta je ova klasa
public class DriverDocument {
    private String name;
    private int documentImage;  //todo kako se sladisti slika?
    private Driver driver;

    public DriverDocument()
    {

    }

    // ! Nemam

    public DriverDocument(String name, int documentImage, Driver driver) {
        this.name = name;
        this.documentImage = documentImage;
        this.driver = driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(int documentImage) {
        this.documentImage = documentImage;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "DriverDocument{" +
                "name='" + name + '\'' +
                ", documentImage=" + documentImage +
                ", driver=" + driver +
                '}';
    }
}
