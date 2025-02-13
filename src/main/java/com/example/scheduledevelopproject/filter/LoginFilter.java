package com.example.scheduledevelopproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/users/signup", "/login", "/logout"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request; // 다운 캐스팅
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response; // 다운 캐스팅

        // 로그인을 체크 해야하는 URL인지 검사
        if (!isWhiteList(requestURI)) {

            // 로그인 확인
            // 세션이 존재하면 가져오고, 없다면 session의 값은 null
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않았다면 예외를 던짐
            if (session == null || session.getAttribute("loginUser") == null) {
                throw new RuntimeException("로그인을 하지 않았습니다.");
            }
        }

        chain.doFilter(request, response);
    }


    // WHITE_LIST에 있는 문자열 존재 여부 확인 메서드
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
