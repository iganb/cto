package com.example.cto.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.controller.dto.EmployeeDto;
import com.example.cto.controller.dto.UserDto;
import com.example.cto.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cto.entity.Order;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
public interface IEmployeeService extends IService<Employee> {

    EmployeeDto login(EmployeeDto employee);

    //司机
    Page<Employee> findDriverPage(Page<Employee> objectPage, String realname, String phone);

    List<Employee> getDrivers();

    Page<Order> findDriverOrder(Page<Order> objectPage, Integer driverId);


    //快递员
    Page<Employee> findCourierPage(Page<Employee> objectPage, String realname, String phone);

    List<Employee> getCouriers();

    Page<Order> findCourierOrder(Page<Order> objectPage, Integer courierId);

    int getEmpNumber();
}
