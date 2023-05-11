package com.example.cto.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.entity.UserAddress;
import com.example.cto.service.IUserAddressService;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IAddressService;
import com.example.cto.entity.Address;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-07
 */
@RestController
@RequestMapping("/address")
        public class AddressController {
    
@Resource
private IAddressService addressService;

@Resource
private IUserAddressService userAddressService;



@PostMapping("/addAddress/{id}")
public Result save(@RequestBody Address address,@PathVariable Integer id) {

        int aId = addressService.addAddress(address);

        UserAddress userAddress=new UserAddress();
        userAddress.setAId(aId);
        userAddress.setUId(id);
        userAddressService.save(userAddress);
        return Result.success();


        }
 @PostMapping("/changeDefault/{uid}/{id}")
 public Result changeDefault(@PathVariable Integer uid,@PathVariable Integer id){
//        将用户所有的地址变为普通地址
         addressService.updateDefaultZero(uid);
//设为默认地址
         addressService.toBeDefault(id);
         return Result.success();
 }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(addressService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(addressService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(addressService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(addressService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(addressService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

