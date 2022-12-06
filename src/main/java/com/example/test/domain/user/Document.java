package com.example.test.domain.user;


public class Document {
    private Long id;
    private String name;
    private String documentImage;
    private Driver driver;

    public Document()
    {

    }

    public Document(String name, String documentImage, Driver driver) {
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

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentImage='" + documentImage + '\'' +
                ", driver=" + driver +
                '}';
    }
}
