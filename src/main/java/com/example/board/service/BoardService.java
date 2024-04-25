package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResponseDTO;

public interface BoardService {

     Long register(BoardDTO boardDTO);

     void delete1(Long bno);

     BoardDTO readOne(Long bno);

     void modify(BoardDTO boardDTO);

     PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}
