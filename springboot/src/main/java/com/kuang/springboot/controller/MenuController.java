package com.kuang.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.common.Constants;
import com.kuang.springboot.common.Result;
import com.kuang.springboot.entity.Dict;
import com.kuang.springboot.entity.Menu;
import com.kuang.springboot.mapper.DictMapper;
import com.kuang.springboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-03
 */
@RestController
@RequestMapping("/menu")
public class MenuController {


//    ???
    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private IMenuService menuService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(menuService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll( @RequestParam(defaultValue = "") String name) {

        return Result.success(menuService.findMenus(name));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String path) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {

            queryWrapper.like("name", name);
        }
        if (!"".equals(path)) {

            queryWrapper.like("path", path);
        }
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @GetMapping("/icons")

    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(null));
    }

}

