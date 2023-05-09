package com.kuang.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 夏年眠
 * @since 2023-04-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> findPage(Page<User> page,
                        @Param("username") String username,
                        @Param("email") String email,
                        @Param("address")String address);
}
