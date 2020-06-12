package com.example.demo.connections;

import com.example.demo.datasource.DynSimpleDataSource;
import com.example.demo.datasource.SimpleDataSource;

import java.sql.*;

/**
 * 通过数据库连接池来完成数据库连接
 * @author wl
 * @date 2020-06-11 15:59:32
 */
public class ConnectionCreateByConnPool {
//    private static SimpleDataSource dataSource = new SimpleDataSource();
    private static DynSimpleDataSource dataSource = new DynSimpleDataSource();


//  创建数据库连接的封装接口
    public  static Connection getConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        return connection;
    }

//  创建关闭数据库连接的封装接口
    public static void closeConnection(ResultSet resultSet, Statement statement,Connection connection) throws SQLException{
            if (null != resultSet){
                resultSet.close();
            }
            if (null != statement){
                statement.close();
            }
            if (null != connection){
                dataSource.closeConnection(connection);
            }
    }
}
