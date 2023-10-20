package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    public static final int USER_NOT_FOUND = 0;//使用者不存在
    public static final int USER_FOUND = 1;//使用者存在
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {//Constructor
        this.repository = repository;
    }

    public void createUser(String account, String password) {//創立新使用者並保存到資料庫
        User user = new User(account, password);
        this.repository.save(user);
    }

    public int isAccountExists(String account) {//使用者帳號是否存在
        User result = this.repository.findByAccount(account);
        if (result != null) return USER_FOUND;
        return USER_NOT_FOUND;
    }

    public boolean isValidUser(String account, String password) {//使用者帳號密碼是否正確
        if (account != "" && password != "") {
            return true;
        }
        return false;
    }

}
