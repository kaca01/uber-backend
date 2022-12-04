package com.example.test.domain.user;


import java.util.Arrays;

public class DriverDocument {
    private String name;
    private byte[] documentImage;
    private Driver driver;

    public DriverDocument()
    {

    }

    public DriverDocument(String name, byte[] documentImage, Driver driver) {
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

    public byte[] getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(byte[] documentImage) {
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
                ", documentImage=" + Arrays.toString(documentImage) +
                ", driver=" + driver +
                '}';
    }
}
