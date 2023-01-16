package com.example.test.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class LoginDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @NotNull
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @NotNull
    private String password;
    private String accessToken;
    private String refreshToken;

    // response
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // request
    public LoginDTO(String accessToken, String refreshToken, boolean flag) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
