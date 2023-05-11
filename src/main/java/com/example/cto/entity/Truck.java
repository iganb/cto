package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-12-15
 */
@Getter
@Setter
  @ApiModel(value = "Truck对象", description = "")
public class Truck implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("货车id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("货车类型")
      private String type;

      @ApiModelProperty("重量")
      private String weight;




}
