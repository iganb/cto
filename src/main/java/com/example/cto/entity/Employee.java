package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2022-12-13
 */
@Getter
@Setter
  @ApiModel(value = "Employee对象", description = "")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("员工id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("员工姓名")
      private String realname;

      @ApiModelProperty("员工电话")
      private String phone;

      @ApiModelProperty("员工头像")
      private String avatarUrl;

      @ApiModelProperty("角色")
      private String role;

      private Integer truckId;

      private Integer status;
      @ApiModelProperty("创建时间")
      @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
      private LocalDateTime createTime;

  @ApiModelProperty("更新时间")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime updateTime;

      @TableField(exist = false)
      private String type;
  @TableField(exist = false)
  private String address;
      private Integer netpointId;
}
