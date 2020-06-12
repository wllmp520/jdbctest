package com.example.demo;

import com.example.demo.connections.ConnectionCreate;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TranscationTest03 {
    /**
     * 关闭数据库连接的自动提交功能，保证事务的特性
     * @author wl
     * @date 2020-06-11 15:18:11
     * @param
     * @return void
     */
    @Test
    public void transcation1() throws Exception{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "update t_user set money=money-1000 where id=13";//wangwenjun的钱减少1000元
            stmt.execute(sql);
            sql="select money from t_user where id=14";
            rs = stmt.executeQuery(sql);
            float money = 0;
            if(rs.next()){
                money = rs.getFloat(1);
                if(money>10000){
                    throw new Exception("钱已到上线，无法在增加");
                }
            }
            sql = "update t_user set money=money+1000 where id=14";
            stmt.execute(sql);
        } finally {
            ConnectionCreate.closeConnection(rs, stmt, conn);
        }
        conn.commit();
    }

}
