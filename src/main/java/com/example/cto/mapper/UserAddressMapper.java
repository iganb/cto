package com.example.cto.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {


    Page<UserAddress> findPage(Page<UserAddress> userAddressPage,@Param("name") String name,@Param("uId") Integer uId);


}
