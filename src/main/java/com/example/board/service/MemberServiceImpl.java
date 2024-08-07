package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.domain.MemberRole;
import com.example.board.dto.MemberJoinDTO;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException{

        String mid = memberJoinDTO.getMid();

        boolean exist = memberRepository.existsById(mid);

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        //비밀번호  Encoding은 서비스 레이어로 빼기?
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        //MemberRole은 여기서 직접 넣어줌.
        member.addRole(MemberRole.USER);

        log.info("=======================");
        log.info(member);
        log.info(member.getRoleSet());
        memberRepository.save(member);
    }w
    @Override
    public void modify(MemberJoinDTO memberJoinDTO)throws MidExistException {

        String mid = memberJoinDTO.getMid();


         if(memberJoinDTO.getMpw().equals("1111")){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        log.info("=======================");
        log.info(member);
        memberRepository.save(member);
    }


}