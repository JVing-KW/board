package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate;

    private  LocalDateTime modDate;

}
