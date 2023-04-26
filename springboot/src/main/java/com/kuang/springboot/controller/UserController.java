package com.kuang.springboot.controller;

import com.kuang.springboot.entity.User;
import com.kuang.springboot.mapper.UserMapper;
import com.kuang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public  Integer save(@RequestBody User user){
        return  userMapper.insert(user);
    };


    @GetMapping()
    public List<User> index(){
        return userMapper.findAll();
    };
}
