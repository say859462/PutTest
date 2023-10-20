package com.example.demo.Controller;

import com.example.demo.service.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User request) {

        System.out.println("Hi");

        String account = request.getAccount();
        String password = request.getPassword();
        // 帳好只能包含英文和數字(??) 應該要允許可以特殊符號之類的
        if (!account.matches("^[A-Za-z0-9]+$")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "帳號必須由英文字母和數字組成"));
            //return ResponseEntity.badRequest().body("帳號必須由英文字母和數字組成。");
        }
        // 在資料庫中檢查帳號是否已經存在
        if (userService.isAccountExists(account) == UserService.USER_FOUND) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "帳號已存在"));
        }


        // 創建使用者並保存到資料庫
        userService.createUser(account, password);

        return ResponseEntity.ok(Collections.singletonMap("message", "註冊成功!"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User request) {
        String account = request.getAccount();
        String password = request.getPassword();

        if (userService.isValidUser(account, password)) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "註冊成功!");
            response.put("location", "main.html");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "登入失敗，帳號或密碼錯誤"));
        }
    }
}
