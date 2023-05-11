package com.example.cto.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Constants;
import com.example.cto.common.Result;
import com.example.cto.controller.dto.EmployeeDto;
import com.example.cto.controller.dto.UserDto;
import com.example.cto.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IEmployeeService;
import com.example.cto.entity.Employee;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    @PostMapping("/login")
    public Result login(@RequestBody EmployeeDto employee) {
        String password = employee.getPassword();
        String username = employee.getUsername();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        EmployeeDto dto = employeeService.login(employee);
        return  Result.success(dto);
    }
    @PostMapping
    public Result saveEmp(@RequestBody Employee employee) {
        LocalDateTime now = LocalDateTime.now();

        employee.setUpdateTime(now);
        return Result.success(employeeService.saveOrUpdate(employee));
    }

    @PostMapping("/saveDriver")
    public Result save(@RequestBody Employee employee) {
        LocalDateTime now = LocalDateTime.now();

        employee.setUpdateTime(now);
        employee.setRole("ROLL_DRIVER");
        return Result.success(employeeService.saveOrUpdate(employee));
    }
    @PostMapping("/saveCourier")
    public Result saveCourier(@RequestBody Employee employee) {
        LocalDateTime now = LocalDateTime.now();

        employee.setUpdateTime(now);
        employee.setRole("ROLL_COURIER");
        return Result.success(employeeService.saveOrUpdate(employee));
    }
    @GetMapping("/username/{username}")
    public Result findUserByName(@PathVariable String username) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return Result.success(employeeService.getOne(wrapper));
    }
    @GetMapping("/getDrivers")
    public Result getDrivers(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("role","ROLL_DRIVER");
        List<Employee> driverList=employeeService.getDrivers();
        return Result.success(driverList);
    }
    @GetMapping("/getCourier")
    public Result getCourier(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("role","ROLL_COURIER");
        List<Employee> driverList=employeeService.getCouriers();
        return Result.success(driverList);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(employeeService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(employeeService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(employeeService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(employeeService.getById(id));
    }

    @GetMapping("/driverPage")
    public Result findDriverPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String realname,
                           @RequestParam String phone) {

        Page<Employee> page = employeeService.findDriverPage(new Page<>(pageNum, pageSize), realname, phone);
        return Result.success(page);
    }
    @GetMapping("/courierPage")
    public Result findCourierPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String realname,
                           @RequestParam String phone) {

        Page<Employee> page = employeeService.findCourierPage(new Page<>(pageNum, pageSize), realname, phone);
        return Result.success(page);
    }
    public int getEmpNumber(){
        return employeeService.getEmpNumber();
    }


}

