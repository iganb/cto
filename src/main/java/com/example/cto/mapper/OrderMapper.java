package com.example.cto.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.Home;
import com.example.cto.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.ResultMap;

import java.sql.ResultSet;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2022-12-09
 */
public interface OrderMapper extends BaseMapper<Order> {


    @Update("update userorder set payment_status=#{status},alipay_no=#{alipayTradeNo} where order_number=#{tradeNo}")
    void updateState(@Param("tradeNo") String tradeNo,@Param("status") Integer status,@Param("alipayTradeNo") String alipayTradeNo);

    Page<Order> findAllPage(Page<Order> orderPage,@Param("orderNumber") String orderNumber,@Param("wayBillNumber") String wayBillNumber,@Param("senderName") String senderName,@Param("addresseeName") String addresseeName);

    Page<Order> fenDriverPage(Page<Object> objectPage,@Param("orderNumber") String orderNumber,@Param("wayBillNumber") String wayBillNumber,@Param("senderName") String senderName,@Param("addresseeName") String addresseeName);

    Page<Order> driverPage(Page<Object> objectPage,@Param("driverId") Integer driverId);

    @Select("select count(*) from userorder")
    int findOrderNumber();

    List<Home> findOrderTime();

    @Select("select sum(goods_price) from userorder where status!=6 and status!=7")
    int getIncome();


    Page<Order> fenCourierPage(Page<Object> objectPage, String orderNumber, String wayBillNumber, String senderName, String addresseeName);

    Page<Order> courierPage(Page<Object> objectPage, Integer courierId);
}
