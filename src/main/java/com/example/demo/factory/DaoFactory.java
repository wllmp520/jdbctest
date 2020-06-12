package com.example.demo.factory;

import com.example.demo.dao.UserDao;

/**
 * Dao工厂
 * @author wl
 * @date 2020-06-11 13:43:06
 */
public interface DaoFactory {
    public UserDao createUserDao();
}
