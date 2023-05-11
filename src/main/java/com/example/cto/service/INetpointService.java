package com.example.cto.service;

import com.example.cto.entity.Netpoint;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2023-04-05
 */
public interface INetpointService extends IService<Netpoint> {

    public boolean queryNewProductExists(String address);
}
