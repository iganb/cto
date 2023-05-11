package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author 张驰
 * @Date 2022/11/19 10:04
 * @Version 1.0
 */
@TableName("role_menu")
@Data
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;

}
