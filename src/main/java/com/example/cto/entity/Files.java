package com.example.cto.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
  @ApiModel(value = "Files对象", description = "")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("文件id")
      @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

      @ApiModelProperty("文件名")
      private String name;

      @ApiModelProperty("文件大小")
      private Long size;

      @ApiModelProperty("类型")
      private String type;

      @ApiModelProperty("路径")
      private String url;

      @ApiModelProperty("是否删除")
      private Boolean isDelete;

      @ApiModelProperty("是否禁用")
      private Boolean enable;

      private String md5;
}
