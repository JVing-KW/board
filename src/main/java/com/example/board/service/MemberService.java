package com.example.board.service;

import com.example.board.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

}
