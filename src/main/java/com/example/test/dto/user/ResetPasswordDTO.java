package com.example.test.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResetPasswordDTO {
    private String newPassword;
    private String code;

    // request
    public ResetPasswordDTO(String newPassword, String code) {
        this.newPassword = newPassword;
        this.code = code;
    }
}
