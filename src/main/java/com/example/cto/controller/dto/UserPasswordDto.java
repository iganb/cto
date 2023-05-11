package com.example.cto.controller.dto;

import lombok.Data;

/**
 * @Author 张驰
 * @Date 2022/12/4 10:31
 * @Version 1.0
 */
@Data
public class UserPasswordDto {
    private String username;
    private String password;
    private String phone;
    private String newPassword;

}
