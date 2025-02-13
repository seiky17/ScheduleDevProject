package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.UpdateUserRequestDto;
import com.example.scheduledevelopproject.dto.UserRequestDto;
import com.example.scheduledevelopproject.dto.UserResponseDto;
import com.example.scheduledevelopproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@Validated @RequestBody UserRequestDto requestDto) {

        // userService의 createUser 메서드 호출
        UserResponseDto userResponseDto = userService.createUser(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    // 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findByIdUser(@PathVariable Long id) {

        // userService의 findByIdUser 메서드 호출
        UserResponseDto userResponseDto = userService.findByIdUser(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable Long id,
            @Validated @RequestBody UpdateUserRequestDto requestDto
    ) {
        // userService의 updateUser 메서드 호출
        userService.updateUser(id, requestDto.getUsername(), requestDto.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        // userService의 deleteUser 메서드 호출
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
