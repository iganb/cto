package com.example.cto.mapper;

import com.example.cto.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
public interface AddressMapper extends BaseMapper<Address> {

    List<Address> findAddressById(@Param("id") Integer id);

    void updateDefaultZero(@Param("uId") Integer uId);

    void toBeDefault(@Param("id")Integer id);
    
}
