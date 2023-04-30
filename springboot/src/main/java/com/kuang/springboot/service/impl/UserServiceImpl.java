package com.kuang.springboot.service.impl;

import com.kuang.springboot.entity.User;
import com.kuang.springboot.mapper.UserMapper;
import com.kuang.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 夏年眠
 * @since 2023-04-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
