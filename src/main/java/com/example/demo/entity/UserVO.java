package com.example.demo.entity;

public class UserVO {

    /**
     * id : long //用户ID
     * name : string //用户名
     * phone : long //电话
     * avatar : string //头像
     * gender : byte //性别
     */

    private String id;
    private String name;
    private String phone;
    private String avatar;
    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
