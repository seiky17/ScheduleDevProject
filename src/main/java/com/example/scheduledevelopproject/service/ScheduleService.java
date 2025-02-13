package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedule;
import com.example.scheduledevelopproject.entity.User;
import com.example.scheduledevelopproject.repository.ScheduleRepository;
import com.example.scheduledevelopproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 할일 생성 서비스 로직
    public ScheduleResponseDto save(String title, String contents, String username) {

        // findByUsernameOrElseThrow 메서드를 사용하여 User 객체를 찾음
        User findUser = userRepository.findByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);

        // schedule에 있는 User 객체를 지정
        schedule.setUser(findUser);

        // save 메소드 호출
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getUser().getUsername());
    }

    // 할일 전체 조회 서비스 로직
    public List<ScheduleResponseDto> findAllSchedule() {

        // findAll 메서드를 호출하여 얻은 ScheduleResponseDto 객체들을 List로 변환하여 반환
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    // 할일 단건 조회 서비스 로직
    public ScheduleResponseDto findByIdSchedule(Long id) {

        // findByIdOrElseThrow 메서드 호출
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername());
    }

    // 할일 수정 서비스 로직
    @Transactional
    public void updateSchedule(Long id, String title, String contents) {

        // findByIdOrElseThrow 메서드 호출
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // findSchedule의 updateSchedule 메서드 호출
        findSchedule.updateSchedule(title, contents);
    }


    // 할일 삭제 서비스 로직
    public void deleteSchedule(Long id) {

        // findByIdOrElseThrow 메서드 호출
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // delete 메서드 호출
        scheduleRepository.delete(findSchedule);
    }
}
