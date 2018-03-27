package com.lh.cloud.vo;

import java.io.Serializable;

public class UserVo implements Serializable {
    private String id;
    private String userName;
    private String passWord;
    private int age;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public UserVo() {
    }

    public UserVo(String id, String userName, String passWord, int age,String ip) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
