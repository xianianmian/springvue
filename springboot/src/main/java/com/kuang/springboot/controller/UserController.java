package com.kuang.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuang.springboot.service.IUserService;
import com.kuang.springboot.entity.User;


import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 夏年眠
 * @since 2023-04-30
 */
@Controller
@RequestMapping("/user")
        public class UserController {
    
@Resource
private IUserService userService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
        }

@GetMapping
public List<User> findAll() {
        return userService.list();
        }

@GetMapping("/{id}")
public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
        }

@GetMapping("/page")
public Page<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return userService.page(new Page<>(pageNum, pageSize));
        }

        }

