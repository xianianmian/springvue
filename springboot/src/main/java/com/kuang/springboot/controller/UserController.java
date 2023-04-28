package com.kuang.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.entity.User;
import com.kuang.springboot.mapper.UserMapper;
import com.kuang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    //曾
    @Autowired
    private UserService userService;

    @PostMapping
    public boolean save(@RequestBody User user){
        return  userService.saveUser(user);
    };
    //查
    @Autowired
    private UserMapper userMapper;
    @GetMapping()
    public List<User> findAll(){
//        List<User> all = userMapper.findAll();
//        return all;
        return userService.list();
    };
    //删
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
//        return userMapper.deleteById(id);
        return userService.removeById(id);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
//        return userMapper.deleteById(id);
        return userService.removeByIds(ids);
    }


    //分页查
//    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum,
//                                       @RequestParam Integer pageSize,
//                                       @RequestParam String username){
//        pageNum = (pageNum - 1)* pageSize;
//        List<User> all = userMapper.selctPage(pageNum,pageSize,username);
//        Integer total = userMapper.selctTotal();
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",all);
//        res.put("total",total);
//
//        return res;
//    };
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String username,
                                        @RequestParam(defaultValue = "") String email,
                                        @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){

            queryWrapper.like("username",username);
        }
        if(!"".equals(email)) {
            queryWrapper.like("email", email);
        }
            if(!"".equals(address)) {
                queryWrapper.like("address", address);
            }
            queryWrapper.orderByDesc("id");
        return   userService.page(page,queryWrapper);

    };

}
