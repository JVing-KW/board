package org.zerock.bulletin.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.*;



@Entity
@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(length = 500, nullable = false) //컬럼의 길이와 null허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    //변경을 위한 생성자 어떻게
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

}
