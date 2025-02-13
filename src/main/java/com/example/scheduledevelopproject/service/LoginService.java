package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.UserResponseDto;
import com.example.scheduledevelopproject.entity.User;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    // 로그인 서비스 로직
    public UserResponseDto login(String email, String password) {
        // findUserByEmailAndPasswordElseThrow 메서드를 사용하여 User 객체를 찾음
        User findUser = userRepository.findUserByEmailAndPasswordElseThrow(email, password);

        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }
}
