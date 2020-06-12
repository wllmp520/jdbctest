package com.example.demo.datasource;

import com.example.demo.connections.DynSimpleConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 动态代理数据源
 * @author wl
 * @date 2020-06-11 17:41:48
 */
public class DynSimpleDataSource {
    private static LinkedList<Connection> connPool = null;

    private static int minCount = 5;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connPool = new LinkedList<Connection>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DynSimpleDataSource(){
        for(int i = 0;i<minCount;i++){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
                DynSimpleConnection dynSimpleConnection = new DynSimpleConnection(this);
                Connection connWarp=dynSimpleConnection.bind(conn);
                connPool.addFirst(connWarp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception{
        synchronized (connPool) {
            if(connPool.isEmpty())
                throw new Exception("have no connection now!");
            return connPool.removeLast();
        }
    }


    /**
     * 释放数据库连接
     * @param conn
     * @throws SQLException
     */
    public void closeConnection(Connection conn) throws SQLException{
        synchronized (connPool) {
            connPool.addFirst(conn);
        }
    }

    /**
     * 获取当前数据库连接数
     * @return
     */
    public int getCurrentCount(){
        synchronized (connPool) {
            return connPool.size();
        }
    }
}
