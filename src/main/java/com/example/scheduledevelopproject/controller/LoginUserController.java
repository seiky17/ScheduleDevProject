package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.LoginUserRequestDto;
import com.example.scheduledevelopproject.dto.UserResponseDto;
import com.example.scheduledevelopproject.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginUserController {

    private final LoginService loginService;

    // 로그인
    @PostMapping("/login")
    public String login(
            @Validated @ModelAttribute LoginUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        // 로그인 서비스
        UserResponseDto responseDto = loginService.login(requestDto.getEmail(), requestDto.getPassword());

        // 세션을 가져오기 -> 만약 세션이 없다면 새로 만든다
        HttpSession session = request.getSession();

        // Session에 responseDto 저장
        session.setAttribute("loginUser", responseDto);

        return "로그인에 성공하였습니다!";
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Request 객체 내에 Session이 존재한다면 기존 Session을 반환
        // 만약 Request 객체 내에 Session이 없다면 null을 반환
        HttpSession session = request.getSession(false);

        // session이 null이 아니라면 해당 세션을 삭제
        if(session != null) {
            session.invalidate();
        }

        return "로그아웃에 성공하였습니다!";
    }
}
