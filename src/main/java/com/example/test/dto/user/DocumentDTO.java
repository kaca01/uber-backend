package com.example.test.dto.user;

import com.example.test.domain.user.Document;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DocumentDTO {

    private Long id;
    private String name;
    private String documentImage;
    private Long driverId;

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

}
