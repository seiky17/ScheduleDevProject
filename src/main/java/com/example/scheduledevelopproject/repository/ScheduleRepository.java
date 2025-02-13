package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // id가 존재한다면 찾은 객체를 반환, 만약 존재하지 않다면 예외를 던짐
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "id를 찾을 수 없습니다."));
    }
}
