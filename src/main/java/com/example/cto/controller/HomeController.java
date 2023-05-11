package com.example.cto.controller;

import com.example.cto.common.Result;
import com.example.cto.entity.Home;
import com.example.cto.service.IComplaintService;
import com.example.cto.service.IEmployeeService;
import com.example.cto.service.IOrderService;
import com.example.cto.service.IUserService;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张驰
 * @Date 1/4/2023 下午3:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Resource
    private IOrderService orderService;
    @Resource
    private IUserService userService;
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IComplaintService complaintService;

    @GetMapping("/checkHome")
    public Result checkHome(Home home) {
        List<String> month = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        int orderNumber = orderService.findOrderNumber();
        int userNumber = userService.getUserNumber();
        List<Home> timeCount = orderService.findOrderTime();
        int empNumber = employeeService.getEmpNumber();
        int income = orderService.getIncome();
        int touSuNumber = complaintService.getTouSuNumber();
        for (Home h : timeCount) {
            month.add(h.getCreateTime());
            counts.add(h.getCount());

        }
        home.setUserNumber(userNumber);
        home.setOrderNumber(orderNumber);
        home.setMonth(month);
        home.setCounts(counts);
        home.setEmpNumber(empNumber);
        home.setIncome(income);
        home.setTouSuNumber(touSuNumber);
        System.out.println(home.toString());
        return Result.success(home);
    }
}
