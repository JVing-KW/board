package com.example.board.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {
    // 로그인이 되지 않은 경우는 302에러가 나고 Spring Security가 로그인창으로 이동하지만
    // 로그인 후 권한이 없는 경우 403에러가 나고 따로 Spring Security가 처리해주는 부분이 없어 설정해준다.
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.info("==========Access Denied================!");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        //JSON 요청이었는지 확인

        String contentType = request.getHeader("Content-Type");

        boolean jsonRequest = contentType.startsWith("application/json");

        log.info("isJson : " + jsonRequest);

        //Json 처리 요청은 아직

        //일반 요청

        if(!jsonRequest){
            response.sendRedirect("/member/login?=ACCESS_DENIED");
        }
    }
}
