package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.NotNull;
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
 * @since 2023-03-16
 */
@Getter
@Setter
  @ApiModel(value = "Complaint对象", description = "")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("投诉id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("运单号")
      private String  wayBillNumber;

      @ApiModelProperty("投诉原因")
      private String cause;

      @ApiModelProperty("反馈结果")
      private String result;

  @ApiModelProperty("创建时间")
  @NotNull
  @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Timestamp createTime;

  @ApiModelProperty("结束时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Timestamp overTime;


}
