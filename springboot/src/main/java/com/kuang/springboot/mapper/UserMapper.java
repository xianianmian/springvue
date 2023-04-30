package com.kuang.springboot.mapper;

import com.kuang.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
