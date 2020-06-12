package com.example.demo.factory.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.factory.DaoFactory;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.connections.ConnectionCreate;

import java.sql.Connection;

public class SimpleDaoFacoty implements DaoFactory {
    @Override
    public UserDao createUserDao() {
        Connection connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        UserDao userDao = new UserDaoImpl(connection);
        return userDao;
    }
}
