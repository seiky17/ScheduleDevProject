package com.example.scheduledevelopproject.dto;

import com.example.scheduledevelopproject.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final String username;

    public ScheduleResponseDto(Long id, String title, String contents, String username) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    // Schedule 객체를 ScheduleResponseDto 객체로 변환
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getUsername());
    }
}
