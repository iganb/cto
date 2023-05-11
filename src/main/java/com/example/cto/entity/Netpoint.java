package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author zc
 * @since 2023-04-05
 */
@Getter
@Setter
@ToString
  @ApiModel(value = "Netpoint对象", description = "")
public class Netpoint implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("网点id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("网点位置")
      private String address;

      private Double lng;
      private Double lat;
}
