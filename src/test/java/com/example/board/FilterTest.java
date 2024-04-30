package com.example.board;

import com.example.login.service.CustomUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilterTest {
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Test
    public void filterTest(){
        customUserDetailService.loadUserByUsername("user2");

    }
}
