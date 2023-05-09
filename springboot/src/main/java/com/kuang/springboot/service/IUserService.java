package com.kuang.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.controller.dto.UserDTO;
import com.kuang.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类 22222
 * </p>
 *
 * @author 夏年眠
 * @since 2023-04-30
 */
public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);
    User register(UserDTO userDTO);

    Page<User> findPage(Page<User> page, String username, String email, String address);
}
