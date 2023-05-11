package com.example.cto.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cto.entity.Menu;
import com.example.cto.mapper.MenuMapper;
import com.example.cto.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> findMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }

        //查出所有数据
        List<Menu> menuList = list(queryWrapper);


        //查出pid为null的一级菜单
        List<Menu> parentNode = menuList.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //查出一级菜单的子菜单
        for (Menu menu : parentNode) {
            //筛选所有数据中pid=父级id的二级菜单
            menu.setChildren(menuList.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));

        }
        return parentNode;
    }
}
