package com.kuang.springboot.service;

import com.kuang.springboot.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-03
 */
public interface IRoleService extends IService<Role> {
     void setRoleMenu(Integer roleId, List<Integer> menuIds);

     List<Integer> getRoleMenu(Integer roleId);
}
