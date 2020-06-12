package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.factory.DaoFactory;
import com.example.demo.factory.impl.SimpleDaoFacoty;
import com.example.demo.entity.User;
import com.example.demo.exception.JDBCException;
import com.example.demo.connections.ConnectionCreate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.Date;

@SpringBootTest
class DaoDaoImplTests02 {
    private  UserDao userDao;

    {
        DaoFactory daoFactory = new SimpleDaoFacoty();
        userDao = daoFactory.createUserDao();
    }

    /**
     * 实现Dao的使用
     * @author wl
     * @date 2020-06-10 17:38:01
     * @param
     * @return void
     */
    @Test
    public void crud() throws JDBCException {
        User user = new User();
        //user.setId(6);
        user.setName("firstTWo");
        user.setBirthday(new Date());
        user.setMoney(50);
        int i = userDao.addUser(user);
        System.out.println("插入结果: "+(i>0));

      /*  User user1 = userDao.queryUserById(7);
        System.out.println("id7的结果"+user1.toString());

        List<User> firstN = userDao.queryUserForList("firstN");
        System.out.println("list的结果"+firstN.iterator().next());

        int i1 = userDao.deleteUser(user1);
        System.out.println("删除结果: "+(i1>0));

        int i2 = userDao.updateUser(user, 6);
        System.out.println("更新结果: "+(i2>0));*/
    }
    /**
     * 测试时间类型的存储 setDate() $ setTimestamp()
     * @author wl
     * @date 2020-06-10 17:35:28
     * @param
     * @return void
     */
    @Test
    public void insertByPrepareStatement() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        String sql =
                "INSERT INTO `wl_cache`.`employee`(`lastName`, `email`, `gender`, `d_id`, `add_time`) VALUES ('王林', '2554156', 1, 1, ?)";
        statement = connection.prepareStatement(sql);
//        使用setDate() 无法存储 时间单位,只能存储到 单位天
//        statement.setDate(1,new Date(System.currentTimeMillis()));
//        使用 setTimestamp()可解决问题
        statement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
        statement.execute();
        ConnectionCreate.closeConnection(null,statement,connection);
    }

}
