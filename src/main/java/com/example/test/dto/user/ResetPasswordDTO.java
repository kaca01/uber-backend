package com.example.test.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Data
public class ResetPasswordDTO {
    @NotEmpty
    @NotNull
    @Pattern(regexp = "'^(?=.\\d)(?=.[A-Z])(?!.*[^a-zA-Z0-9@#$^+=])(.{8,15})$'\n")
    private String newPassword;
    @NotEmpty
    @NotNull
    @Pattern(regexp = "'^[0-9]{6}$'")
    private String code;

    // request
    public ResetPasswordDTO(String newPassword, String code) {
        this.newPassword = newPassword;
        this.code = code;
    }
}
