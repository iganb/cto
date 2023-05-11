package com.example.cto.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
public interface IUserAddressService extends IService<UserAddress> {


    Page<UserAddress> findPage(Page<UserAddress> userAddressPage, String name, Integer uId);


}
