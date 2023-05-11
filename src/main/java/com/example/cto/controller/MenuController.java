package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Constants;
import com.example.cto.common.Result;
import com.example.cto.entity.Dist;
import com.example.cto.mapper.DistMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IMenuService;
import com.example.cto.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/menu")
        public class MenuController {
    
@Resource
private IMenuService menuService;

        @Resource
        private DistMapper distMapper;

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Menu menu) {
                return Result.success(menuService.saveOrUpdate(menu));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(menuService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(menuService.removeByIds(ids));
        }



        @GetMapping
        public Result findAll(@RequestParam(defaultValue = "") String name) {

                return Result.success(   menuService.findMenus(name));
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(menuService.getById(id));
        }

        @GetMapping("/ids")
        public Result findAllIdS() {
                return Result.success(menuService.list().stream().map(Menu::getId));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name) {
                QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name", name);
                return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        @GetMapping("/icons")
        public Result getIcons(){
                QueryWrapper<Dist> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
                System.out.println(queryWrapper);
                return Result.success(distMapper.selectList(queryWrapper));
        }
        }

