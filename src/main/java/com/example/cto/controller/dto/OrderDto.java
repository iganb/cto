package com.example.cto.controller.dto;

import lombok.Data;

/**
 * @Author 张驰
 * @Date 2022/12/10 14:46
 * @Version 1.0
 */
@Data
public class OrderDto {
    private String fullAddress;
    private Integer gid;
    private String name;
    private String goodsNumber;
    private String pickupType;
    private String payType;
    private String phone;
    private String goodsPrice;
    private String senderRegion;

    private String fullAddress2;
    private String name2;
    private String phone2;
    private String addresseeRegion;
    private Integer uid;
    private String goodsName;

}
