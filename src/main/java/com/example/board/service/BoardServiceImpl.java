package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResponseDTO;
import com.example.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);

        Long bno = boardRepository.save(board).getBno();

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
        log.info(modelMapper.map(board, BoardDTO.class));
        return modelMapper.map(board, BoardDTO.class);
    }

    // 1. findById로 Optional<T> 클래스를 사용해서 예외처리까지
    // - 조회 ~ 예외처리까지 전자가 더 편한고 안정적임
    // 2. 그냥 builder로 새로 엔티티 만들어서 save
    @Override
    public void modify(BoardDTO boardDTO) {
//     Board board = Board.builder()
//              .bno(boardDTO.getBno())
//              .title(boardDTO.getTitle()+"22")
//              .writer(boardDTO.getWriter()+"22").build();


        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        log.info("result.. :" + result);

        Board board = result.orElseThrow();
        log.info("board.. :" + board);

        board.change(boardDTO.getTitle(), boardDTO.getWriter());

        boardRepository.save(board);

    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();

        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = pageRequestDTO.getPagebla("bno");

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO).dtoList(dtoList)
                .total((int) result.getTotalElements()).build();

    }
}
