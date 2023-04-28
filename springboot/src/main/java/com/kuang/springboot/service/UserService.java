package com.kuang.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuang.springboot.entity.User;
import com.kuang.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public Boolean saveUser(User user) {
//        if(user.getId() == null){
//           return save(user);
//        }else{
//          return  updateById(user);
//        }
        return saveOrUpdate(user);
    }
//    @Autowired
//    private UserMapper userMapper;
//    public  Integer save(User user){
//        if(user.getId() == null){
//           return userMapper.insert(user);
//        }else{
//           return userMapper.update(user);
//        }
//    }
}
