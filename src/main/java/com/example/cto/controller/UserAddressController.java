package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.service.IAddressService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IUserAddressService;
import com.example.cto.entity.UserAddress;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
@RestController
@RequestMapping("/user-address")
public class UserAddressController {

    @Resource
    private IUserAddressService userAddressService;


    @Resource
    private IAddressService addressService;

    @PostMapping
    public Result save(@RequestBody UserAddress userAddress) {
        return Result.success(userAddressService.saveOrUpdate(userAddress));
    }

    @DeleteMapping("/deleteAddress/{id}/{uId}")
    public Result deleteAddress(@PathVariable Integer id,@PathVariable Integer uId){
       QueryWrapper<UserAddress> queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("u_id",uId);
       queryWrapper.eq("a_id",id);
       addressService.removeById(id);
        userAddressService.remove(queryWrapper);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userAddressService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userAddressService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userAddressService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userAddressService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name,
                           @RequestParam Integer uId) {
        Page<UserAddress> page = userAddressService.findPage(new Page<>(pageNum, pageSize), name,uId);
        System.out.println("+++++++++++++++"+page.getRecords().toString());
        return Result.success(page);
    }

}

