package com.example.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailService implements UserDetailsService {
    //스프링 시큐리티에서 가장 중요한 객체로 실제로 인증을 처리하는 UserDetailsService라는 구현체
    //실제 인증을 처리할 때 호출되는 부분 loadUserByUsername

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = User.builder()
                .username("user1111")
                .password("1111")
                .authorities("ROLE_USER")
                .build();
        return userDetails;
    }
}