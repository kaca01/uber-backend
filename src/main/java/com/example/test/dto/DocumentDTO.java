package com.example.test.dto;

import com.example.test.domain.user.Document;

public class DocumentDTO {

    private Long id;
    private String name;
    private String documentImage;
    private Long driverId;


    public DocumentDTO() {
    }

    public DocumentDTO(Document document) {
        this(document.getId(), document.getName(), document.getDocumentImage(), document.getDriver().getId());
    }

    // response
    public DocumentDTO(Long id, String name, String documentImage, Long driverId) {
        super();
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }

    // request
    public DocumentDTO(String name, String documentImage) {
        super();
        this.name = name;
        this.documentImage = documentImage;
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

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
