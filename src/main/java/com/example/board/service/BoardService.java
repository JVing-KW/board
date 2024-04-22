package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResponseDTO;
import org.springframework.data.domain.Page;

public interface BoardService {

     Long register(BoardDTO boardDTO);

     void delete1(Long bno);

     BoardDTO readOne(Long bno);

     void modify(BoardDTO boardDTO);

     PageResponseDTO<BoardDTO> getlist(PageRequestDTO pageRequestDTO);

}
