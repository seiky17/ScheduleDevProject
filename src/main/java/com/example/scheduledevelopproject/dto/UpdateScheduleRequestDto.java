package com.example.scheduledevelopproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    @NotNull
    @Size(max = 20, message = "제목은 20자 이하여야 합니다.")
    private final String title;

    @NotNull
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
