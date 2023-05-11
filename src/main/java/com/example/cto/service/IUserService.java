package com.example.cto.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cto.controller.dto.UserDto;
import com.example.cto.entity.User;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2022-11-01
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto);


    User register(UserDto userDto);

    void saveByPhone(String phone2);

    int getUserNumber();


//
//    Page<User> findPage(Page<User> objectPage, String username, String email, String address);
}
