package com.example.reader.bean;

import org.litepal.crud.DataSupport;

//数据表
public class User extends DataSupport {
    private int id;
    private String account;
    private String password;

    public User(){}

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
