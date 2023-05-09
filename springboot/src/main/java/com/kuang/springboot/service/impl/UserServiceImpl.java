package com.kuang.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.common.Constants;
import com.kuang.springboot.controller.dto.UserDTO;
import com.kuang.springboot.entity.Menu;
import com.kuang.springboot.entity.User;
import com.kuang.springboot.exception.ServiceException;
import com.kuang.springboot.mapper.RoleMapper;
import com.kuang.springboot.mapper.RoleMenuMapper;
import com.kuang.springboot.mapper.UserMapper;
import com.kuang.springboot.service.IMenuService;
import com.kuang.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuang.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类 333
 * </p>
 *
 * @author 夏年眠
 * @since 2023-04-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private  RoleMenuMapper roleMenuMapper;

    @Autowired
    private IMenuService MenuService;

    @Autowired
    private  UserMapper userMapper;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            String Token =  TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(Token);

            String role = one.getRole();
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
//        System.out.println(one);
        return one;
    }

    @Override
    public Page<User> findPage(Page<User> page, String username, String email, String address) {

        return userMapper.findPage(page,username,email,address);
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    private List<Menu> getRoleMenus(String roleFlag){
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        List<Menu> menus = MenuService.findMenus("");
        List<Menu> roleMenus = new ArrayList<>();

//            menus.stream().filter(m->menuIds.contains(m.getId())).collect(Collectors.toList());
        for(Menu menu:menus){
            if(menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child->!menuIds.contains(child.getId()));
        }
        return  roleMenus;
    }
}