package org.zerock.bulletin;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@Log4j2
@SpringBootTest
public class jpaTest {

    @Autowired
   private  DataSource dataSource;

    @Test
    public void test1() throws SQLException {

  Connection conn = dataSource.getConnection();

  log.info(conn);
        Assertions.assertNotNull(conn);
    }

}
