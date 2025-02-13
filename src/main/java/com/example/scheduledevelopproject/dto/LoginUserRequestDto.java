package com.example.scheduledevelopproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginUserRequestDto {

    // Validation 적용
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    @Size(max = 10, message = "비밀번호는 10자 이하여야 합니다.")
    private final String password;

    public LoginUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
