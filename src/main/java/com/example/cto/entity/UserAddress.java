package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
  @TableName("user_address")
@ApiModel(value = "UserAddress对象", description = "")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("用户id")
        private Integer uId;

      @ApiModelProperty("地址id")
        private Integer aId;


  @TableField(exist = false)
  List<Address> addressList;

}
