package com.kuang.springboot.controller.dto;

import com.kuang.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 222000
 *
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private  Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String Token;
    private String role;

    private List<Menu> menus;
}
