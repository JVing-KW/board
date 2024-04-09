package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;
import org.springframework.data.domain.Page;

public interface BoardService {

    public Long register(BoardDTO boardDTO);

    public void delete1(Long bno);

    public BoardDTO readOne(Long bno);

    public void modifiy(BoardDTO boardDTO);

}
