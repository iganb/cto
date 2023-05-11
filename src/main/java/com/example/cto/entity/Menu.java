package com.example.cto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
  @ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("菜单id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("图标")
      private String icon;

      @ApiModelProperty("父级id")
      private Integer pid;

      @ApiModelProperty("页面路径")
      private String pagePath;

      @ApiModelProperty("路径")
      private String path;

  @TableField(exist = false)
  private List<Menu> children;
}
