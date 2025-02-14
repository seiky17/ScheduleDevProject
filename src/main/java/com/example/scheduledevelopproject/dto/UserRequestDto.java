package com.example.scheduledevelopproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    // Validation 적용
    @NotNull
    @Size(max = 4, message = "작성자명은 4자 이하여야 합니다.")
    private final String username;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    @Size(max = 10, message = "비밀번호는 10자 이하여야 합니다.")
    private final String password;

    public UserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
