package com.example.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Board extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private String writer;

    // update는 등록 시간이 필요하므로 가능하면 findbyById()로 가져온 객체를 이용해서
    // 약간의 수정을 통해서 처리하도록 한다.
    //findbyById로 조회하는게 더 부담이 될 수 있지 않을까?

    public void change(String title,String content){
        this.title = title;
        this.content = content;
    }
}
