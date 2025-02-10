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

    public UserResponseDto createUser(String username, String email) {

        User user = new User(username, email);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findByIdUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }

    @Transactional
    public void updateUser(Long id, String username, String email) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.updateUser(username, email);
    }

    @Transactional
    public void deleteUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }


}
