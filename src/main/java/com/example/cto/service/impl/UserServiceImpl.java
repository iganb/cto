package com.example.cto.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cto.common.Constants;
import com.example.cto.controller.dto.UserDto;
import com.example.cto.entity.User;
import com.example.cto.exception.ServiceException;

import com.example.cto.mapper.UserMapper;

import com.example.cto.service.IUserService;
import com.example.cto.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-11-01
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG=Log.get();

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDto login(UserDto userDto) {
       User one=getUserInfo(userDto);
        if (one!=null){

            BeanUtil.copyProperties(one, userDto, true);

            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDto.setToken(token);
            System.out.println("登录用户："+userDto);


            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }

    }

    @Override
    public User register(UserDto userDto) {
        System.out.println(userDto);
        User one=getUserInfo(userDto);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDto.getUsername());
        wrapper.eq("phone",userDto.getPhone());
        User two=getOne(wrapper);

//        有快递信息但未注册用户
        QueryWrapper<User> wrapper2= new QueryWrapper<>();
        wrapper2.eq("username",userDto.getUsername());
        User three=getOne(wrapper2);
        QueryWrapper<User> wrapper3= new QueryWrapper<>();
        wrapper3.eq("phone",userDto.getPhone());
        User four=getOne(wrapper3);
        if (one == null && two==null){
            one=new User();
            LocalDateTime now = LocalDateTime.now();
            one.setCreateTime(now);
            BeanUtil.copyProperties(userDto, one, true);
            save(one);//把copy完的用户存到数据库
        }else if (three==null && four!=null){
            four=new User();
            LocalDateTime now = LocalDateTime.now();
            four.setCreateTime(now);
            BeanUtil.copyProperties(userDto, four, true);
            saveOrUpdate(four);//更新用户
        }
        else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");

        }
        return one;
    }

    @Override
    public void saveByPhone(String phone2) {
        userMapper.saveByPhone(phone2);
    }

    @Override
    public int getUserNumber() {
        return userMapper.getUserNumber();
    }


//    @Override
//    public Page<User> findPage(Page<User> userPage, String username, String email, String address) {
//        return userMapper.findPage(userPage,username,email,address);
//    }

    private User getUserInfo(UserDto userDto){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        queryWrapper.eq("password", userDto.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }


}
