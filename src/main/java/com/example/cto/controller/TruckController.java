package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.ITruckService;
import com.example.cto.entity.Truck;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-15
 */
@RestController
@RequestMapping("/truck")
        public class TruckController {
    
@Resource
private ITruckService truckService;


@PostMapping
public Result save(@RequestBody Truck truck) {
        return Result.success(truckService.saveOrUpdate(truck));
}

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(truckService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(truckService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(truckService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(truckService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam String type) {
        QueryWrapper<Truck> queryWrapper = new QueryWrapper<>();
        if (!"".equals(type)) {
                queryWrapper.like("name", type);
        }
        return Result.success(truckService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

