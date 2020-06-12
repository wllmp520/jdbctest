package com.example.demo.entity;

import java.util.Date;

/**
 * 数据库实体类 user
 * @author wl
 * @date 2020-06-11 10:03:44
 * @return null
 */
public class User {
    private int id;
    private Date birthday;
    private String name;
    private float money;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getMoney() {
        return money;
    }
    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
