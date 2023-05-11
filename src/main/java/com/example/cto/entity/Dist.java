package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张驰
 * @Date 2022/11/18 15:44
 * @Version 1.0
 */
@Getter
@Setter
@TableName("dist")
public class Dist {


    private String name;
    private String value;
    private String type;
}
