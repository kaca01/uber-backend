package com.example.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        super();
        this.message = message;
    }

}
