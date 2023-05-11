package com.example.cto.controller.dto;


import com.example.cto.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @Author 张驰
 * @Date 2022/11/7 10:59
 * @Version 1.0
 */
@Data
public class EmployeeDto {
    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String phone;
    private String avatarUrl;
    private String token;

    private String role;
    private List<Menu> menus;
}
