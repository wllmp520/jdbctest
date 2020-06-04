package com.example.demo.entity;

import java.sql.*;

public class ConnectionCreate {
//    静态代码块初始化一次 数据库驱动连接
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    创建数据库连接的封装接口
    public  static Connection getConnection(String url,String username,String password){
        Connection connection = null;
        try {
             connection= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
//    创建关闭数据库连接的封装接口
    public static void closeConnection(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (null != resultSet){
                resultSet.close();
            }
            if (null != statement){
                statement.close();
            }
            if (null != connection){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
