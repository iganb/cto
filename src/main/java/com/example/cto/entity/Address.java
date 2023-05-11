package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
@Getter
@Setter
  @ApiModel(value = "Address对象", description = "")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("地址id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("省市区")
      private String region;

      @ApiModelProperty("详细地址")
      private String fullAddress;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("电话")
      private String phone;

      private Integer defaultNum;

      @TableField(exist = false)
      private Integer uId;

}
