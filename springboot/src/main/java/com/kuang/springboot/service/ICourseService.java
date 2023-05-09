package com.kuang.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-08
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> page, String name);

    void setStudentCourse(Integer courseId, Integer studentId);
}
