package com.example.test.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Document {
    private Long id;
    private String name;
    private String documentImage;
    private Driver driver;

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
