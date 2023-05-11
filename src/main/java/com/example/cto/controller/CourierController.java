package com.example.cto.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.entity.Order;
import com.example.cto.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author 张驰
 * @Date 2023/4/12 19:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/courier")
public class CourierController {
    @Resource
    private IOrderService orderService;
    @GetMapping("/courierPage")
    public Result courierPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam Integer courierId

    ) {

        Page<Order> page = orderService.courierPage(new Page<>(pageNum, pageSize),courierId);
        return Result.success(page);
    }

    @PostMapping("/toCustomer")
    public Result toCustomer(@RequestBody Order order){
        LocalDateTime now = LocalDateTime.now();
        order.setShouoverTime(now);
        order.setStatus(5);
        return Result.success(orderService.saveOrUpdate(order));
    }
}
