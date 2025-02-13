package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.CreateScheduleRequestDto;
import com.example.scheduledevelopproject.dto.ScheduleResponseDto;
import com.example.scheduledevelopproject.dto.UpdateScheduleRequestDto;
import com.example.scheduledevelopproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 할일 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Validated @RequestBody CreateScheduleRequestDto requestDto) {

        // scheduleService의 save 메서드 호출
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 할일 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {

        // scheduleService의 findAllSchedule 메서드 호출
        List<ScheduleResponseDto> findSchedules = scheduleService.findAllSchedule();

        return new ResponseEntity<>(findSchedules, HttpStatus.OK);
    }

    // 할일 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findByIdSchedule(@PathVariable Long id) {

        // scheduleService의 findByIdSchedule 메서드 호출
        ScheduleResponseDto findSchedule = scheduleService.findByIdSchedule(id);

        return new ResponseEntity<>(findSchedule, HttpStatus.OK);
    }

    // 할일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable Long id,
            @Validated @RequestBody UpdateScheduleRequestDto requestDto
    ) {
        // scheduleService의 updateSchedule 메서드 호출
        scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 할일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {

        // scheduleService의 deleteSchedule 메서드 호출
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
