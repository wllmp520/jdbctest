package com.example.demo.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 创建一个链表结构的Connection列表有限集合，供给调配，最简单的数据库连接池
 * @author wl
 * @date 2020-06-11 15:38:39
 */
public class SimpleDataSource {
    private static LinkedList<Connection> connPool = null;

//  静态代码块只加载一次,而驱动加载也只需要一次
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connPool = new LinkedList<Connection>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SimpleDataSource() {
//      创建连接池
        for (int i = 0; i <10 ; i++) {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
                connPool.addFirst(conn);
                //System.out.println(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//  获取连接池枷锁
    public Connection getConnection() throws Exception {
        synchronized (connPool) {
            if(connPool.isEmpty()){
                throw new Exception("No Connection Is Empty Now!");
            }
            Connection connection = connPool.removeLast();
            System.out.println(connection);
            return connection;
        }
    }

    public void closeConnection(Connection connection){
        connPool.addFirst(connection);
    }
}
