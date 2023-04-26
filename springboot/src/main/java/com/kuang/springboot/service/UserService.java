package com.kuang.springboot.service;

import com.kuang.springboot.entity.User;
import com.kuang.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public  Integer save(User user){
        if(user.getId() == null){
           return userMapper.insert(user);
        }else{
          return  userMapper.update(user);
        }
    }
}
