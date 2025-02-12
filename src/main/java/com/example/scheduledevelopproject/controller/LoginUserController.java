package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.LoginUserRequestDto;
import com.example.scheduledevelopproject.dto.UserResponseDto;
import com.example.scheduledevelopproject.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginUserController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserRequestDto requestDto, HttpServletRequest request) {

        UserResponseDto responseDto = loginService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = request.getSession();

        session.setAttribute("loginUser", responseDto);

        return "로그인에 성공하였습니다!";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "로그아웃에 성공하였습니다!";
    }
}
