package com.example.login.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private long bno;

    private String id;

    private String name;

    private String password;

    private LocalDateTime regDate;

    private LocalDateTime moddate;
}
