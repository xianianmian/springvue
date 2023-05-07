package com.kuang.springboot.service;

import com.kuang.springboot.entity.Menu;
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
public interface IMenuService extends IService<Menu> {


    List<Menu> findMenus(String name);
}
