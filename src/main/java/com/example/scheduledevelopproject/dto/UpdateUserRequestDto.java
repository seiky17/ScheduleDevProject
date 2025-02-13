package com.example.scheduledevelopproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    // Validation 적용
    @NotNull
    @Size(max = 4, message = "작성자명은 4자 이하여야 합니다.")
    private final String username;

    @NotBlank
    @Email
    private final String email;

    public UpdateUserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
