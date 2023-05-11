package com.example.cto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zc
 * @since 2022-12-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("userOrder")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    private String wayBillNumber;

    @ApiModelProperty("寄件人电话")
    private String senderPhone;

    @ApiModelProperty("寄件人姓名")
    private String senderName;

    @ApiModelProperty("寄件人地址")
    private String senderAddress;

    @ApiModelProperty("收件人姓名")
    private String addresseeName;

    @ApiModelProperty("收件人电话")
    private String addresseePhone;

    @ApiModelProperty("收件人地址")
    private String addresseeAddress;

    @ApiModelProperty("取件类型")
    private String pickupType;

    @ApiModelProperty("付款方式")
    private String payType;

    @ApiModelProperty("是否付款，1：已支付，0：未支付")
    private Integer paymentStatus;

    @ApiModelProperty("货物名字")
    private String goodsName;

    @ApiModelProperty("货物数量")
    private String goodsNumber;

    @ApiModelProperty("订单创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private String alipayNo;

    private String senderRegion;


    private String addresseeRegion;
    @ApiModelProperty("状态,0:待取件，1：已取件，2：已取消")
    private Integer status;

    private Integer uid;

    private String goodsPrice;


    private Integer driverId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime shouTime;
    private Integer courierId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime jiTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime jioverTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime shouoverTime;
    @TableField(exist = false)
    private String driverName;
    @TableField(exist = false)
    private String courierName;
}
