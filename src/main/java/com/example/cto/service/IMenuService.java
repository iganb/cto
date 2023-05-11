package com.example.cto.service;

import com.example.cto.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
