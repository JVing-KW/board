package com.example.board;

import com.example.board.domain.Board;
import com.example.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Test
    public void searchTest(){

        Pageable pageable =PageRequest.of(1,10, Sort.by("bno").descending());

        boardRepository.search1(pageable);

    }



    @Test
    public void repoTest()  {
        IntStream.rangeClosed(1,100).forEach(i ->{
            Board board=  Board.builder()
                    .title("제목..")
                    .content("내용..")
                    .writer("기우..")
                    .build();
            boardRepository.save(board);
        });
    }
    @Test
    public void selectTest(){
        Long bno = 100L;
          Optional<Board> result1 =boardRepository.findById(bno);
        Optional<Board> result2 =boardRepository.findById(101L); //null로 값이 반환되지 않게 해줌
        boardRepository.findById(102L);
        log.info(result1);
        log.info(result2);

    }

    @Test
    public void pageTest(){
        Pageable pageable =  PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> result =boardRepository.findAll(pageable);
    }

    @Test
    public void testSearchAll(){
        Pageable pageable = PageRequest.of(1,10,Sort.by("bno").descending());
        String[] types = {"t","c","w"};
        String keyword = "1";
      Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
        log.info(result);
    }
    @Test
    public void testSearchAll2(){
        Pageable pageable = PageRequest.of(1,10,Sort.by("bno").descending());
        String[] types = {"t","c","w"};
        String keyword = "1";
        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
        log.info(result.getTotalPages());
    }

}
