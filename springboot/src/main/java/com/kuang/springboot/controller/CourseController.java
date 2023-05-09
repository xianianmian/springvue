package com.kuang.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.common.Result;
import com.kuang.springboot.entity.Course;
import com.kuang.springboot.entity.User;
import com.kuang.springboot.service.ICourseService;
import com.kuang.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-08
 */
@RestController
@RequestMapping("/course")
public class CourseController {

//    @Resource
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Course course) {
        courseService.saveOrUpdate(course);
        return Result.success();
    }

    @PostMapping("/studentCourse/{courseId}/{studentId}")
    public Result studentCourse(@PathVariable Integer courseId,@PathVariable Integer studentId) {
        courseService.setStudentCourse(courseId,studentId);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }



    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(20);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
//        Page<Course> page = courseService.page(new Page<>(pageNum,pageSize),queryWrapper);
//        List<Course> records = page.getRecords();
//        for(Course record:records){
//            User user = userService.getById(record.getTeachId());
//            if(user != null){
//                record.setTeacher(user.getNickname());
//            }
//        }
        Page<Course> page = courseService.findPage(new Page<>(pageNum,pageSize),name);
        return Result.success(page);
    }

}

