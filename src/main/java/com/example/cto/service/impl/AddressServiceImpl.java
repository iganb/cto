package com.example.cto.service.impl;

import com.example.cto.entity.Address;
import com.example.cto.mapper.AddressMapper;
import com.example.cto.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Resource
private AddressMapper addressMapper;

    @Override
    public int addAddress(Address address) {
        int aid = addressMapper.insert(address);
        Integer addressId = address.getId();
        return addressId;
    }

    @Override
    public List<Address> findAddressById(Integer id) {
        return addressMapper.findAddressById(id);
    }

    @Override
    public void updateDefaultZero(Integer uId) {
        addressMapper.updateDefaultZero(uId);
    }

    @Override
    public void toBeDefault(Integer id) {
        addressMapper.toBeDefault(id);
    }
}
