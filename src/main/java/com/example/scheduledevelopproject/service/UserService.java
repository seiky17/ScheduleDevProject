package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.UserResponseDto;
import com.example.scheduledevelopproject.entity.User;
import com.example.scheduledevelopproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성 서비스 로직
    public UserResponseDto createUser(String username, String email, String password) {

        User user = new User(username, email, password);

        // save 메서드 호출
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // 유저 조회 서비스 로직
    public UserResponseDto findByIdUser(Long id) {

        // findByIdOrElseThrow 메서드 호출
        User findUser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }

    // 유저 수정 서비스 로직
    @Transactional
    public void updateUser(Long id, String username, String email) {

        // findByIdOrElseThrow 메서드 호출
        User findUser = userRepository.findByIdOrElseThrow(id);

        // findUser의 updateUser 메서드 호출
        findUser.updateUser(username, email);
    }

    // 유저 삭제 서비스 로직
    public void deleteUser(Long id) {

        // findByIdOrElseThrow 메서드 호출
        User findUser = userRepository.findByIdOrElseThrow(id);

        // delete 메서드 호출
        userRepository.delete(findUser);
    }


}
