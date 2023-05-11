package com.example.cto.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.cto.entity.Menu;
import com.example.cto.entity.Role;
import com.example.cto.entity.RoleMenu;
import com.example.cto.mapper.RoleMapper;
import com.example.cto.mapper.RoleMenuMapper;
import com.example.cto.service.IMenuService;
import com.example.cto.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    //如果方法内包含多线程的使用，方法内部出现异常，
    //  不会回滚线程中调用方法的事务
    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
//        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id",roleId);
//        roleMenuMapper.delete(queryWrapper);

        //先删除当前id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);

        List<Integer> menuIdsCopy= CollUtil.newArrayList(menuIds);
        //再把前端传过来的菜单id绑定到角色id
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid()!=null && !menuIdsCopy.contains(menu.getPid())){//二级菜单 并且传过来没有父级id
                //将父级id存入
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {

        return roleMenuMapper.selectByRoleId(roleId);
    }
}
