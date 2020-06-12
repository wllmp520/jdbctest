package com.example.demo;

import com.example.demo.connections.ConnectionCreate;
import com.example.demo.connections.ConnectionCreateByConnPool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

@SpringBootTest
public class ConnPoolTests04 {
    @Test
    public void createDataSource() throws Exception {
        for(int i = 0;i<20;i++){
            Connection conn= ConnectionCreateByConnPool.getConnection();
            ConnectionCreateByConnPool.closeConnection(null, null, conn);
        }
    }

    @Test
    public void createDataSource2() throws Exception{
        for(int i = 0;i<10;i++){
            Connection conn= ConnectionCreateByConnPool.getConnection();
            conn.close();
            System.out.println(conn);
        }
    }

}
