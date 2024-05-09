package com.example.board;


import com.example.board.domain.Member;
import com.example.board.domain.MemberRole;
import com.example.board.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    public void jo(){
      Optional<Member> member = memberRepository.getWithRoles("member1");
      log.info(member);
    }

}
