package com.example.cto.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.controller.dto.OrderDto;
import com.example.cto.entity.Employee;
import com.example.cto.entity.Home;
import com.example.cto.entity.User;
import com.example.cto.service.IEmployeeService;
import com.example.cto.service.IUserService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IOrderService;
import com.example.cto.entity.Order;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-09
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IUserService userService;

@Resource
private IEmployeeService employeeService;

    @PostMapping
    public Result save(@RequestBody Order order) {
        return Result.success(orderService.saveOrUpdate(order));
    }

    @PostMapping("/saveDriver")
    public Result saveDriver(@RequestBody Order order) {
        LocalDateTime now = LocalDateTime.now();
        order.setShouTime(now);
        order.setStatus(3);
        UpdateWrapper<Employee> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("status",1).eq("id",order.getDriverId());
        employeeService.update(updateWrapper);
        return Result.success(orderService.saveOrUpdate(order));
    }
    @PostMapping("/saveCourier")
    public Result saveCourier(@RequestBody Order order) {
        LocalDateTime now = LocalDateTime.now();
        order.setShouTime(now);
        order.setStatus(8);//改为状态寄送中
        UpdateWrapper<Employee> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("status",1).eq("id",order.getCourierId());
        employeeService.update(updateWrapper);
        return Result.success(orderService.saveOrUpdate(order));
    }
    @PostMapping("/addOrder")
    public Result saveOrder(@RequestBody OrderDto orderDto){

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",orderDto.getPhone2());
        System.out.println(orderDto.toString());
        List<User> list = userService.list(queryWrapper);
        System.out.println("list:"+list.toString());
        if (list!=null){
            User user=new User();
            user.setPhone(orderDto.getPhone2());

            userService.saveByPhone(orderDto.getPhone2());
        }

        Order order = orderService.addOrder(orderDto);
        orderService.save(order);
        return Result.success(order);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(orderService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(orderService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(orderService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(orderService.getById(id));
    }

//    public Result findByWillBill(@PathVariable String wayBillNumber){
//        return Result.success(orderService.findByWillBill(wayBillNumber));
//    }

    @GetMapping("/findJipage")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String username,
                           @RequestParam Integer uid) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.ne("status",6);
        if (!"".equals(username)) {
            queryWrapper.like("sender_name", username);
        }
        return Result.success(orderService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @GetMapping("/findShoupage")
    public Result findShoupage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String username,
                               @RequestParam String phone) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("addressee_name",username);
        queryWrapper.eq("addressee_phone",phone);
        if (!"".equals(username)) {
            queryWrapper.like("addressee_name", username);
        }
        return Result.success(orderService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @GetMapping("/findAllPage")
    public Result findAllPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                              @RequestParam String orderNumber,
                              @RequestParam String wayBillNumber,
                              @RequestParam String senderName,
                              @RequestParam String addresseeName
                              ) {

        Page<Order> page = orderService.findAllPage(new Page<>(pageNum, pageSize), orderNumber, wayBillNumber, senderName, addresseeName);
        return Result.success(page);
    }

    @GetMapping("/fenDriverPage")
    public Result fenDriverPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam String orderNumber,
                              @RequestParam String wayBillNumber,
                              @RequestParam String senderName,
                              @RequestParam String addresseeName
    ) {

        Page<Order> page = orderService.fenDriverPage(new Page<>(pageNum, pageSize), orderNumber, wayBillNumber, senderName, addresseeName);
        return Result.success(page);
    }

    @GetMapping("/fenCourierPage")
    public Result fenCourierPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam String orderNumber,
                                @RequestParam String wayBillNumber,
                                @RequestParam String senderName,
                                @RequestParam String addresseeName
    ) {

        Page<Order> page = orderService.fenCourierPage(new Page<>(pageNum, pageSize), orderNumber, wayBillNumber, senderName, addresseeName);
        return Result.success(page);
    }



//    确认收货
    @RequestMapping("/shouHuo/{wayBillNumber}")
    public Result shouHuo(@PathVariable String wayBillNumber){
        System.out.println("运单编号"+wayBillNumber);
        UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("way_bill_number",wayBillNumber);
        updateWrapper.set("status",6);
        orderService.update(updateWrapper);
        return Result.success();
    }

    //查看订单总数
    public int findOrderNumber(){
        int number=orderService.findOrderNumber();
        return number;
    }

    //查看订单时间
    public List<Home> findOrderTime(){

        return   orderService.findOrderTime();
    }

    public int getIncome(){
        return orderService.getIncome();
    }
}

