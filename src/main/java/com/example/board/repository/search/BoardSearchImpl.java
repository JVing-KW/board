package com.example.board.repository.search;

import com.example.board.domain.Board;
import com.example.board.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; // Q도메인 객체
        JPQLQuery<Board> query = from(board); //select.. from board
        query.where(board.title.contains("1")); // where title like 1
        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        //Q도메인 객체 생성
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);



        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if
        // bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch();

        Long count = query.fetchCount();

        //페이징 처리를 위해선 최종 결과를 Page<T>타입으로 반환해야 하는데 이를 직접 처리해주는 불편함이 있어
        //Spring Data Jpa에서 제공하는 PageImpl을 사용해서 Page<T>타입으로 return 해준다
        return new PageImpl<>(list,pageable,count);

    }
}

