package com.example.cto.service;

import com.example.cto.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
public interface IAddressService extends IService<Address> {

    int addAddress(Address address);

    List<Address> findAddressById(Integer id);

    void updateDefaultZero(Integer uId);

    void toBeDefault(Integer id);
}
