package com.example.cto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cto.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2022-11-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void saveByPhone(@Param("phone") String phone2);

    @Select("select count(*) from user")
    int getUserNumber();
}
