package com.example.cto.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cto.entity.Netpoint;
import com.example.cto.mapper.NetpointMapper;
import com.example.cto.service.INetpointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2023-04-05
 */
@Service
public class NetpointServiceImpl extends ServiceImpl<NetpointMapper, Netpoint> implements INetpointService {

    @Override
    public boolean queryNewProductExists(String address) {
        int count = this.count(new LambdaQueryWrapper<Netpoint>()
                .exists("select id from netpoint where address = '" + address + "'")
        );
        if (count > 0) {
            return true;
        }
        return false;
    }
}
