package com.example.cto.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cto.common.Constants;
import com.example.cto.common.Result;
import com.example.cto.controller.dto.UserDto;
import com.example.cto.entity.User;
import com.example.cto.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author 张驰
 * @Date 2022/12/5 16:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        String password = userDto.getPassword();
        String username = userDto.getUsername();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"用户名和密码不能为空");
        }
        UserDto dto = userService.login(userDto);
        return  Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        String password = userDto.getPassword();
        String username = userDto.getUsername();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"用户名和密码不能为空");
        }

        return  Result.success(userService.register(userDto)) ;
    }

    @PostMapping
    public Result save(@RequestBody User user) {

        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now);
        return Result.success(userService.saveOrUpdate(user));
    }

    @GetMapping("/username/{username}")
    public Result findUserByName(@PathVariable String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return Result.success(userService.getOne(wrapper));
    }

    public int getUserNumber(){
        int number=userService.getUserNumber();
        return number;
    }
}
