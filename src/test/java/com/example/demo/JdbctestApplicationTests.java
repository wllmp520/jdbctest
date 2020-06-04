package com.example.demo;

import com.example.demo.entity.ConnectionCreate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.*;

@SpringBootTest
class JdbctestApplicationTests {

    @Test
    void contextLoads1() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
//          1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
//          2.连接数据库
             connection = DriverManager.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
             statement = connection.createStatement();
             resultSet = statement.executeQuery("select * from department");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+resultSet.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (null != resultSet){
                    resultSet.close();
                }
                if (null != statement){
                    statement.close();
                }
                if (null != connection){
                    connection.close();
                }
                Assert.isTrue(connection.isClosed(),"连接未关闭啊！！");
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getJDBCConnection() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        sql = "INSERT INTO `wl_cache`.`employee`(`id`, `lastName`, `email`, `gender`, `d_id`) VALUES (2, '王林2', '255415622', 1, 1)";
        String sql2 = "INSERT INTO `wl_cache`.`employee`(`id`, `lastName`, `email`, `gender`, `d_id`) VALUES (3, '王林3', '255415622', 1, 1)";
        statement = connection.createStatement();
        statement.addBatch(sql);
        statement.addBatch(sql2);
        statement.executeBatch();
        ConnectionCreate.closeConnection(null,statement,connection);
    }

}
