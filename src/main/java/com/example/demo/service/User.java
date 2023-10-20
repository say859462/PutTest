package com.example.demo.service;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Emo_User")
public class User implements Serializable {
    private String account;
    private String password;

    public User() {

    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;

    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }


}
//使用者註冊資料