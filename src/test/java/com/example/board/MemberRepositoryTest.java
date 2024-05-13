package com.example.board;


import com.example.board.domain.Member;
import com.example.board.domain.MemberRole;
import com.example.board.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void jo() {
        Optional<Member> member = memberRepository.getWithRoles("member1");
        log.info(member);
    }

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
                    Member member = Member.builder()
                            .mid("member" + i)
                            .mpw(passwordEncoder.encode("1111"))
                            .email("email" + i + "@aaa.bbb")
                            .build();
            member.addRole(MemberRole.USER);

            if(i >= 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
                }
        );
    }
    @Test
    @Commit //트랜잭션 어노테이션
    public void testUpdate(){
        String mid = "cookie_00@naver.com";

    String mpw = passwordEncoder.encode("54321");
    memberRepository.updatePassword(mpw,mid);
    }

}
