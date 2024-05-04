package com.example.board;


import com.example.board.domain.Member;
import com.example.board.domain.MemberRole;
import com.example.board.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder()
                    .mid("member" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email" + i + "@aaa.bbb")
                    .build();
            member.addRole(MemberRole.USER);

            if(i >= 95){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }
}
