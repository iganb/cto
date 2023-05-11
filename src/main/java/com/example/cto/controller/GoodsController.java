package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IGoodsService;
import com.example.cto.entity.Goods;

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
@RequestMapping("/goods")
        public class GoodsController {
    
@Resource
private IGoodsService goodsService;


@PostMapping
public Result save(@RequestBody Goods goods) {
        return Result.success(goodsService.saveOrUpdate(goods));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(goodsService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(goodsService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(goodsService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(goodsService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam String name) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
                queryWrapper.like("name", name);
        }
        return Result.success(goodsService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        }

