package com.example.board;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.search.BoardSearch;
import com.example.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.time.LocalDateTime.now;

@Log4j2
@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void registerTest(){
     BoardDTO boardDTO  = BoardDTO.builder().
                title("제목").
                content("내용").
                writer("기우").
                regDate(now()).build();
     Long bno = boardService.register(boardDTO);
     log.info(bno);
    }
    @Test
    public void deleteTest( ){


        boardService.delete1(101L);

    }

    @Test
    public void readTest(){
        boardService.readOne(202L);
    }
}

