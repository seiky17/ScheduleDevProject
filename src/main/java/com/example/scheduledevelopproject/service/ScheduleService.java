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

    public ScheduleResponseDto save(String title, String contents, String username) {

        User findUser = userRepository.findByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getUser().getUsername());
    }

    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findByIdSchedule(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername());
    }

    @Transactional
    public void updateSchedule(Long id, String title, String contents) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateSchedule(title, contents);
    }


    public void deleteSchedule(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
