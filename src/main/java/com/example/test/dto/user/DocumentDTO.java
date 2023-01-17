package com.example.test.dto.user;

import com.example.test.domain.user.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class DocumentDTO {

    private Long id;
    @NotEmpty
    @NotNull
    @Length(max = 100)
    private String name;
    @NotEmpty
    @NotNull
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
