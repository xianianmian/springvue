package com.kuang.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-08
 */
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> page,@Param("name") String name);

    void deleteStudentCourse(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);
    void setStudentCourse(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);
}
