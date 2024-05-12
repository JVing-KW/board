package com.example.board.service;

import com.example.board.dto.MemberJoinDTO;
import lombok.extern.log4j.Log4j2;

public interface MemberService {
    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

}
