package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.JDBCException;
import com.example.demo.connections.ConnectionCreate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用原生jdbc去实现Dao层的CRUD操作
 * @author wl
 * @date 2020-06-11 11:00:15
 */
public class UserDaoImpl implements UserDao {

    private Connection conn = null;

    public UserDaoImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public int addUser(User user) throws JDBCException {
        PreparedStatement preparedStatement = null;
        int result =0;
        String sql = "INSERT INTO `wl_cache`.`t_user`(`name`, `birthday`, `money`,id) VALUES (?, ?, ?,?);\n";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setTimestamp(2,new Timestamp(user.getBirthday().getTime()));
            preparedStatement.setFloat(3,user.getMoney());
            preparedStatement.setInt(4,user.getId());
            result=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new JDBCException("新增用户出现异常",e);
        }finally {
            try{
                ConnectionCreate.closeConnection(null,preparedStatement,null);
            }catch (SQLException e){
                throw new JDBCException("释放资源时出现异常!", e);
            }
        }
        return result;
    }

    @Override
    public int deleteUser(User user) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = "delete from t_user where id = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionCreate.closeConnection(null,preparedStatement,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    public User queryUserById(int id) {
        User user = new User();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from t_user where id = ?";
        try {
            preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setBirthday(resultSet.getTimestamp(3));
                user.setMoney(resultSet.getFloat(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionCreate.closeConnection(resultSet,preparedStatement,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> queryUserForList(String name) {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from t_user where name = ?";
        try {
            preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setBirthday(resultSet.getTimestamp(3));
                user.setMoney(resultSet.getFloat(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionCreate.closeConnection(resultSet,preparedStatement,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public int updateUser(User newInfo, int id) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = "update t_user set name=?,birthday=?,money=? where id = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,newInfo.getName());
            preparedStatement.setTimestamp(2,new Timestamp(newInfo.getBirthday().getTime()));
            preparedStatement.setFloat(3,newInfo.getMoney());
            preparedStatement.setInt(4,id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionCreate.closeConnection(null,preparedStatement,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
