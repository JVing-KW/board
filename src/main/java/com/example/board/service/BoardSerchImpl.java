package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;
import com.example.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Log4j2
@Service
public class BoardSerchImpl implements  BoardService{

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Long register(BoardDTO boardDTO) {
     Board board =   modelMapper.map(boardDTO, Board.class);

      Long bno =  boardRepository.save(board).getBno();

      return bno;
    }
    @Override
    public void delete1(Long bno) {
        boardRepository.deleteById(bno);

    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
    log.info(modelMapper.map(board,BoardDTO.class));
        return  modelMapper.map(board,BoardDTO.class);
    }

    //조회 먼저해서
    @Override
    public void modifiy(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        board.change(boardDTO.getTitle(), boardDTO.getWriter());

         boardRepository.save(board);

    }
}
