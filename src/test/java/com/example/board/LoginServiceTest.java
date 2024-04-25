package com.example.board;

import com.example.login.domain.LoginEntity;
import com.example.login.dto.LoginDTO;
import com.example.login.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class LoginServiceTest {



    @Autowired
    DataSource dataSource;

    @Autowired
    LoginService loginService;
    @Test
    public void repoTest(){
        LoginDTO loginDTO = LoginDTO.builder().id("kiwoo").password("12341234").name("기우").build();
        loginService.loginCreate(loginDTO);
    }



    @Test
    public void loginCreateTest()throws SQLException {

      Connection connection = dataSource.getConnection();
log.info(connection);
    }


}
