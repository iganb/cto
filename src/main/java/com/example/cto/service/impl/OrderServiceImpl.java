package com.example.cto.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.controller.OrderController;
import com.example.cto.controller.dto.OrderDto;
import com.example.cto.entity.Home;
import com.example.cto.entity.Order;
import com.example.cto.entity.Role;
import com.example.cto.mapper.OrderMapper;
import com.example.cto.mapper.RoleMapper;
import com.example.cto.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-12-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

@Resource
private OrderMapper orderMapper;



    @Transactional
    @Override
    public Order addOrder(OrderDto orderDto) {
        Order order = new Order();
        long timeMillis = System.currentTimeMillis();
        order.setOrderNumber("cto"+timeMillis);
        order.setWayBillNumber(timeMillis+"u");
        LocalDateTime now = LocalDateTime.now();
        order.setCreateTime(now);
        order.setSenderRegion(orderDto.getSenderRegion());
        order.setAddresseeRegion(orderDto.getAddresseeRegion());
        order.setPickupType(orderDto.getPickupType());
        order.setGoodsNumber(orderDto.getGoodsNumber());
        order.setGoodsPrice(orderDto.getGoodsPrice());
        order.setSenderName(orderDto.getName());
        order.setSenderPhone(orderDto.getPhone());
        order.setSenderAddress(orderDto.getFullAddress());
        order.setAddresseeName(orderDto.getName2());
        order.setAddresseePhone(orderDto.getPhone2());
        order.setAddresseeAddress(orderDto.getFullAddress2());
        order.setPayType(orderDto.getPayType());
        order.setUid(orderDto.getUid());
        order.setGoodsName(orderDto.getGoodsName());
        System.out.println(order.toString());

       return order;
    }

    @Override
    public Page<Order> findAllPage(Page<Order> orderPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName) {
        return orderMapper.findAllPage(orderPage,orderNumber,wayBillNumber,senderName,addresseeName);
    }

    @Override
    public Page<Order> fenDriverPage(Page<Object> objectPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName) {
        return orderMapper.fenDriverPage(objectPage,orderNumber,wayBillNumber,senderName,addresseeName);
    }

    @Override
    public Page<Order> driverPage(Page<Object> objectPage,Integer driverId) {
        return orderMapper.driverPage(objectPage,driverId);
    }

    @Override
    public int findOrderNumber() {
        return orderMapper.findOrderNumber();
    }

    @Override
    public List<Home> findOrderTime() {
        return orderMapper.findOrderTime();
    }

    @Override
    public int getIncome() {
        return orderMapper.getIncome();
    }

    @Override
    public Page<Order> fenCourierPage(Page<Object> objectPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName) {
        return orderMapper.fenCourierPage(objectPage,orderNumber,wayBillNumber,senderName,addresseeName);
    }

    @Override
    public Page<Order> courierPage(Page<Object> objectPage, Integer courierId) {
        return orderMapper.courierPage(objectPage,courierId);
    }


}
