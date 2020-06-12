package com.example.demo;

import com.example.demo.connections.ConnectionCreate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.*;
import java.sql.*;

@SpringBootTest
class JdbctestApplicationTests01 {

    /**
     * jdbc注册驱动连接数据库
     * @author wl
     * @date 2020-06-10 17:36:12
     * @param
     * @return void
     */
    @Test
    void contextLoads1() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
//          1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
//          2.连接数据库
             connection = DriverManager.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
             statement = connection.createStatement();
             resultSet = statement.executeQuery("select * from department");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+resultSet.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (null != resultSet){
                    resultSet.close();
                }
                if (null != statement){
                    statement.close();
                }
                if (null != connection){
                    connection.close();
                }
                Assert.isTrue(connection.isClosed(),"连接未关闭啊！！");
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }

    /**
     * 对连接驱动等代码进行抽取,解耦合（优化 jdbc注册驱动连接数据库 代码）
     * @author wl
     * @date 2020-06-10 17:36:35
     * @param
     * @return void
     */
    @Test
    public void getJDBCConnection() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        sql = "INSERT INTO `wl_cache`.`employee`(`id`, `lastName`, `email`, `gender`, `d_id`) VALUES (2, '王林2', '255415622', 1, 1)";
        String sql2 = "INSERT INTO `wl_cache`.`employee`(`id`, `lastName`, `email`, `gender`, `d_id`) VALUES (3, '王林3', '255415622', 1, 1)";
        statement = connection.createStatement();
        statement.addBatch(sql);
        statement.addBatch(sql2);
        statement.executeBatch();
        ConnectionCreate.closeConnection(null,statement,connection);
    }

    /**
     * executeUpdate()的使用
     * @author wl
     * @date 2020-06-10 17:38:01
     * @param
     * @return void
     */
    @Test
    public void insert() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        String sql = "INSERT INTO `wl_cache`.`employee`(`id`, `lastName`, `email`, `gender`, `d_id`) VALUES (2, '王林2', '255415622', 1, 1)";
        statement =connection.createStatement();
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        ConnectionCreate.closeConnection(null,statement,connection);
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


    /**
     * 插入一个文本类型的数据入库---存库的时候 格式好像会异常
     * @author wl
     * @date 2020-06-11 09:04:30
     * @param
     * @return void
     */
    @Test
    public void insertClobText()  {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        String sql =
                "INSERT INTO `wl_cache`.`clob_test`(`info`) VALUES (?);";
        try{
            statement = connection.prepareStatement(sql);
            File file = new File("E:\\googledownloads\\大唐逍遥驸马爷.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
//        while(bufferedReader.ready()){
//            System.out.println(bufferedReader.readLine());
//        }
            statement.setCharacterStream(1,fileReader,10);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                ConnectionCreate.closeConnection(null,statement,connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取clobtext的信息并写出
     * @author wl
     * @date 2020-06-11 09:25:34
     * @param
     * @return void
     */
    @Test
    public void queryClobText(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        String sql =
                "SELECT * FROM `clob_test` where id=3";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                Reader info = resultSet.getCharacterStream("info");
                BufferedReader bufferedReader = new BufferedReader(info);
                File f = new File("d:\\reader.txt");
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                String tempReadLine = "";
                if((tempReadLine = bufferedReader.readLine()) !=null){
                    System.out.println(tempReadLine);
                    bw.write(tempReadLine+"\n");
                    bw.flush();
                }
                bw.close();fw.close();bufferedReader.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionCreate.closeConnection(resultSet,statement,connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入一个Video/Image Blob类型的数据入库
     * @author wl
     * @date 2020-06-11 09:04:30
     * @param
     * @return void
     */
    @Test
    public void insertBlobText()  {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        String sql =
                "INSERT INTO `wl_cache`.`clob_test`(`info`, `vinfo`) VALUES ( 'dsfdsffffffffffffffffffffffffffffffff', ?)";
        try{
            statement = connection.prepareStatement(sql);
            File file = new  File("C:\\Users\\xsy_wl\\Desktop\\a.webp");
            FileInputStream fileInputStream = new FileInputStream(file);
            statement.setBinaryStream(1,fileInputStream,(int)file.length());
            statement.execute();
            fileInputStream.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                ConnectionCreate.closeConnection(null,statement,connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取blobtext的信息并写出
     * @author wl
     * @date 2020-06-11 09:25:34
     * @param
     * @return void
     */
    @Test
    public void queryBlobText(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionCreate.getConnection("jdbc:mysql://106.15.251.138:3306/wl_cache","root","Q2P88YjE4b23");
        String sql =
                "SELECT * FROM `clob_test` where id=4";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                InputStream is = resultSet.getBinaryStream("vinfo");

                File f = new File("d:\\a.webp");
                FileOutputStream fileOutputStream = new FileOutputStream(f);
                int len = 0;
                byte[] buffers = new byte[1024];
                while((len=is.read(buffers))>0){
                    fileOutputStream.write(buffers, 0, len);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                is.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionCreate.closeConnection(resultSet,statement,connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void compareDate(){
        java.util.Date date1 = new java.util.Date();
        System.out.println(date1);
        System.out.println("=====================");
        java.sql.Date date2 = new java.sql.Date(3434534);
        System.out.println(date2);
    }



}
