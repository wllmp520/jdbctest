package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.exception.JDBCException;

import java.util.List;

/**
 * 用户Dao
 * @author wl
 * @date 2020-06-11 10:18:47
 */
public interface UserDao {

    /**
     * 增加一个用户
     * @param user
     * @return
     */
    public int addUser(User user) throws JDBCException;

    /**
     * 删除一个用户信息
     * @param user
     * @return
     */
    public int deleteUser(User user);

    /**
     * 查询用户信息通过id编号
     * @param id
     * @return
     */
    public User queryUserById(int id);

    /**
     * 查询用户列表通过用户名
     * @param name
     * @return
     */
    public List<User> queryUserForList(String name);

    /**
     * 更新用户信息,根据id编号来更新
     * @param newInfo
     * @param id
     * @return
     */
    public int updateUser(User newInfo,int id);
}

