package com.example.board;

import com.example.board.repository.search.BoardSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@SpringBootTest
public class BoardQueryTest {
    @Autowired
    BoardSearch boardSearch;


}
