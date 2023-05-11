package com.example.cto.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cto.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    Page<Employee> findDriverPage(Page<Employee> objectPage, String realname, String phone);

    List<Employee> getDrivers();

    Page<Order> findDriverOrder(Page<Order> objectPage, Integer driverId);

    List<Employee> getCouriers();

    Page<Order> findCourierOrder(Page<Order> objectPage, Integer courierId);

    Page<Employee> findCourierPage(Page<Employee> objectPage, String realname, String phone);

    @Select("select count(*) from employee")
    int getEmpNumber();
}
