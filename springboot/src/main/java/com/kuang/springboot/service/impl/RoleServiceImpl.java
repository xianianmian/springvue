package com.kuang.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.springboot.entity.Menu;
import com.kuang.springboot.entity.Role;
import com.kuang.springboot.entity.RoleMenu;
import com.kuang.springboot.mapper.RoleMapper;
import com.kuang.springboot.mapper.RoleMenuMapper;
import com.kuang.springboot.service.IMenuService;
import com.kuang.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private IMenuService menuService;

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
//        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id",roleId);
//        roleMenuMapper.delete(queryWrapper);
        roleMenuMapper.deleteRoleId(roleId);

        for(Integer menuId : menuIds){

            /*
            前端改好了，这是后端的改法
            Menu menu = menuService.getById(menuId);
            if(menu.getPid() != null && !menuIds.contains((menu.getPid()))){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
            }
*/

            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Transactional
    @Override
    public List<Integer> getRoleMenu(Integer roleId){
        return roleMenuMapper.selectByRoleId(roleId);

    }
}
