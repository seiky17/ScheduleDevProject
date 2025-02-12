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

    public UserResponseDto login(String email, String password) {
        User findUser = userRepository.findUserByEmailAndPasswordElseThrow(email, password);

        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }
}
