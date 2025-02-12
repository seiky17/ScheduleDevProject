package com.example.scheduledevelopproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotNull
    @Size(max = 20, message = "제목은 20자 이하여야 합니다.")
    private final String title;

    @NotNull
    private final String contents;

    @NotNull
    @Size(max = 4, message = "작성자명은 4자 이하여야 합니다.")
    private final String username;

    public CreateScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
