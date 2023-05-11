package com.example.cto.mapper;

import com.example.cto.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select id from role where flag=#{flag}")
    Integer selectByFlag(String flag);

}
