package com.kuang.springboot.entity;

import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class User {
    private  Integer id;
    private  String username;
    private  String password;
    private  String nickname;
    private  String email;
    private  String phone;
    private  String address;
}
