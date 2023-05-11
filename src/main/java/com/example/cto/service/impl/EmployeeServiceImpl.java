package com.example.cto.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Constants;
import com.example.cto.controller.dto.EmployeeDto;
import com.example.cto.entity.Employee;
import com.example.cto.entity.Menu;
import com.example.cto.entity.Order;
import com.example.cto.entity.User;
import com.example.cto.exception.ServiceException;
import com.example.cto.mapper.EmployeeMapper;
import com.example.cto.mapper.RoleMapper;
import com.example.cto.mapper.RoleMenuMapper;
import com.example.cto.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cto.service.IMenuService;
import com.example.cto.utils.TokenUtils;
import com.example.cto.utils.TokenUtils2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    private static final Log LOG=Log.get();

    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private IMenuService menuService;

    @Override
    public EmployeeDto login(EmployeeDto employeeDto) {
        Employee one=getEmployeeInfo(employeeDto);
        if (one!=null){
            System.out.println(one.getUsername());
            BeanUtil.copyProperties(one, employeeDto, true);
            //设置token
            String token = TokenUtils2.genToken(one.getId().toString(), one.getPassword());
            employeeDto.setToken(token);


            String role = one.getRole();
            //设置用户菜单列表
            List<Menu> roleMenus = getRoleMenus(role);

            employeeDto.setMenus(roleMenus);
            return employeeDto;
        }else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }

    }

    @Override
    public Page<Employee> findDriverPage(Page<Employee> objectPage, String realname, String phone) {
        return employeeMapper.findDriverPage(objectPage,realname,phone);
    }

    @Override
    public List<Employee> getDrivers() {
        return employeeMapper.getDrivers();
    }

    @Override
    public Page<Order> findDriverOrder(Page<Order> objectPage, Integer driverId) {
        return employeeMapper.findDriverOrder(objectPage,driverId);
    }



    @Override
    public Page<Employee> findCourierPage(Page<Employee> objectPage, String realname, String phone) {
        return employeeMapper.findCourierPage(objectPage,realname,phone);
    }

    @Override
    public List<Employee> getCouriers() {
        return employeeMapper.getCouriers();
    }

    @Override
    public Page<Order> findCourierOrder(Page<Order> objectPage, Integer courierId) {
        return employeeMapper.findCourierOrder(objectPage,courierId);
    }

    @Override
    public int getEmpNumber() {
        return employeeMapper.getEmpNumber();
    }

    private Employee getEmployeeInfo(EmployeeDto employee){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username", employee.getUsername());
        queryWrapper.eq("password", employee.getPassword());
        Employee one;
        try {
            one = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
    private List<Menu> getRoleMenus(String roleflag){
        Integer roleId = roleMapper.selectByFlag(roleflag);
        //当前角色拥有的菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        //查出系统所有菜单
        List<Menu> menus = menuService.findMenus("");

        //新建一个筛选完的用户角色菜单集合
        List<Menu> roleMenu=new ArrayList<>();
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())){
                roleMenu.add(menu);
            }
            List<Menu> children=menu.getChildren();
            children.removeIf(child->!menuIds.contains(child.getId()));
        }

        return roleMenu;
    }
}
