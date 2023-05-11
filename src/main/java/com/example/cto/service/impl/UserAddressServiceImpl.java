package com.example.cto.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.UserAddress;
import com.example.cto.mapper.UserAddressMapper;
import com.example.cto.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;


    @Override
    public Page<UserAddress> findPage(Page<UserAddress> userAddressPage, String name, Integer uId) {
        return userAddressMapper.findPage(userAddressPage,name,uId);
    }

}
