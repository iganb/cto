package com.example.cto.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.cto.entity.Address;
import lombok.Data;

import java.util.List;

/**
 * @Author 张驰
 * @Date 2022/12/8 9:59
 * @Version 1.0
 */
@Data
public class UserAddressDto {

    private Integer uId;

    private Integer aId;

    @TableField(exist = false)
    List<Address> addressList;
}
