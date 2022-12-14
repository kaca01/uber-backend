package com.example.test.domain.user;

import javax.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "documentImage", nullable = false)
    private String documentImage;
    @ManyToOne
    private Driver driver;

    public Document()
    {

    }

    public Document(String name, String documentImage, Driver driver) {
        this.name = name;
        this.documentImage = documentImage;
        this.driver = driver;
    }

    public Document(Long id, String name, String documentImage, Driver driver) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
