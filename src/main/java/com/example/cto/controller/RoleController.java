package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IRoleService;
import com.example.cto.entity.Role;

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
@RequestMapping("/role")
        public class RoleController {
    
@Resource
private IRoleService roleService;


        @PostMapping
        public Result save(@RequestBody Role role) {
                return Result.success(roleService.saveOrUpdate(role));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(roleService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(roleService.removeByIds(ids));
        }

        @GetMapping
        public Result findAll() {
                return Result.success(roleService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(roleService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "") String description) {


                QueryWrapper<Role> queryWrapper = new QueryWrapper<>();

                if (!"".equals(name)) {
                        queryWrapper.like("name", name);
                }
//        if (!"".equals(description)) {
//                queryWrapper.like("description", description);
//        }


                return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        /**
         * 绑定角色与菜单的关系
         * @param roleId
         * @param menuIds
         * @return
         */
        @PostMapping("/setRoleMenu/{roleId}")
        public Result setRoleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
                roleService.setRoleMenu(roleId, menuIds);
                return Result.success();
        }


        @GetMapping("/getRoleMenu/{roleId}")
        public Result getRoleMenu(@PathVariable Integer roleId) {

                return Result.success( roleService.getRoleMenu(roleId));
        }

        }

