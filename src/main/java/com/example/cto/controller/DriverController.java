package com.example.cto.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.entity.Employee;
import com.example.cto.entity.Order;
import com.example.cto.service.IEmployeeService;
import com.example.cto.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author 张驰
 * @Date 2023/2/15 10:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/driver")
public class DriverController {


@Resource
private IOrderService orderService;

    @GetMapping("/driverPage")
    public Result driverPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam Integer driverId

    ) {

        Page<Order> page = orderService.driverPage(new Page<>(pageNum, pageSize),driverId);
        return Result.success(page);
    }

    @PostMapping("/toWangDian")
    public Result toWangDian(@RequestBody Order order){
        LocalDateTime now = LocalDateTime.now();
        order.setShouoverTime(now);
        order.setStatus(4);
        return Result.success(orderService.saveOrUpdate(order));
    }
}
