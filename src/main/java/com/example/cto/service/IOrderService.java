package com.example.cto.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.controller.dto.OrderDto;
import com.example.cto.entity.Home;
import com.example.cto.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.mapping.ResultMap;

import java.sql.ResultSet;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2022-12-09
 */
public interface IOrderService extends IService<Order> {


    Order addOrder(OrderDto orderDto);

    Page<Order> findAllPage(Page<Order> orderPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName);

    Page<Order> fenDriverPage(Page<Object> objectPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName);

    Page<Order> driverPage(Page<Object> objectPage,Integer driverId);


    int findOrderNumber();

    List<Home> findOrderTime();

    int getIncome();


    Page<Order> fenCourierPage(Page<Object> objectPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName);

    Page<Order> courierPage(Page<Object> objectPage, Integer courierId);
}
