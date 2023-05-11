package com.example.cto.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.cto.common.Constants;
import com.example.cto.entity.Employee;
import com.example.cto.entity.User;
import com.example.cto.exception.ServiceException;
import com.example.cto.service.IEmployeeService;
import com.example.cto.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 张驰
 * @Date 2022/11/11 15:28
 * @Version 1.0
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {

            throw new ServiceException(Constants.CODE_401, "无token，请重新登录");
        }
        // 获取 token 中的 user id
        String userId;
        String role=null;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            System.out.println("userid"+userId);
//            System.out.println("token++++++++++++++++++++++"+JWT.decode(token).getAudience().get(2));

        } catch (JWTDecodeException j) {

            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        // 根据token中的userid查询数据库
        User user = userService.getById(userId);
        String avatarUrl = user.getAvatarUrl();
        Employee employee=employeeService.getById(userId);
        System.out.println(user.toString());
        System.out.println(employee.toString());
        if (user == null && employee==null ) {
            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录");
        }

        // 用户密码加签验证 token
        JWTVerifier  jwtVerifier=null;
        JWTVerifier  jwtVerifier2=null;
            if (user!=null && role==null && avatarUrl!=null){
                jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            }else if (employee!=null && role==null){
                jwtVerifier2=JWT.require(Algorithm.HMAC256(employee.getPassword())).build();
            }



        try {

           if (jwtVerifier!=null){
               System.out.println(1);
               jwtVerifier.verify(token); // 验证token
           }else {
               System.out.println(2);
               jwtVerifier2.verify(token);
           }

        } catch (JWTVerificationException e) {
            System.out.println("用户密码验证错");
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        return true;

    }


}
