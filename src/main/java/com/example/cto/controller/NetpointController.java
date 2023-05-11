package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.utils.BaiDuMapUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.INetpointService;
import com.example.cto.entity.Netpoint;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zc
 * @since 2023-04-05
 */
@RestController
@RequestMapping("/netpoint")
public class NetpointController {

    @Resource
    private INetpointService netpointService;


    @PostMapping
    public Result save(@RequestBody Netpoint netpoint) {
        System.out.println("网点"+netpoint.toString());
        Map<String, Double> lngAndLat = BaiDuMapUtils.getLngAndLat(netpoint.getAddress());
        netpoint.setLng(lngAndLat.get("lng"));
        netpoint.setLat(lngAndLat.get("lat"));
        boolean b = netpointService.queryNewProductExists(netpoint.getAddress());
        if (b){
            return Result.error();
        }
        return Result.success(netpointService.saveOrUpdate(netpoint));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(netpointService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(netpointService.removeByIds(ids));
    }

    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(netpointService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(netpointService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String address) {
        QueryWrapper<Netpoint> queryWrapper = new QueryWrapper<>();
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return Result.success(netpointService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }



}

