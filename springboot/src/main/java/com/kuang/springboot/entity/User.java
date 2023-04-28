package com.kuang.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@TableName("sys_user")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private  String username;
    @JsonIgnore
    private  String password;
    private  String nickname;
    private  String email;
    private  String phone;
    private  String address;
    @TableField (value="avatar_url")
    private  String avatar;
}
