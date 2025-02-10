package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.CreateScheduleRequestDto;
import com.example.scheduledevelopproject.dto.ScheduleResponseDto;
import com.example.scheduledevelopproject.dto.UpdateScheduleRequestDto;
import com.example.scheduledevelopproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {

        List<ScheduleResponseDto> findSchedules = scheduleService.findAllSchedule();

        return new ResponseEntity<>(findSchedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findByIdSchedule(@PathVariable Long id) {

        ScheduleResponseDto findSchedule = scheduleService.findByIdSchedule(id);

        return new ResponseEntity<>(findSchedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable Long id,
            @RequestBody(required = false) UpdateScheduleRequestDto requestDto
    ) {
        scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
