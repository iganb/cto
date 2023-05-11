package com.example.cto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cto.entity.RoleMenu;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 张驰
 * @Date 2022/11/19 10:05
 * @Version 1.0
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from role_menu where role_id=#{role_id}")
    int deleteByRoleId(Integer roleId);

    @Select("select menu_id from role_menu where role_id=#{role_id}")
    List<Integer> selectByRoleId(Integer roleId);
}
