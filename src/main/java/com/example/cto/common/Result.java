package com.example.cto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 张驰
 * @Date 2022/11/11 8:21
 * @Version 1.0
 */

//接口统一返回包装类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static com.example.cto.common.Result success(){
        return new com.example.cto.common.Result(com.example.cto.common.Constants.CODE_200,"",null);
    }
    public static com.example.cto.common.Result success(Object data){
        return new com.example.cto.common.Result(com.example.cto.common.Constants.CODE_200,"",data);
    }

    public static com.example.cto.common.Result error(String code, String msg){
        return new com.example.cto.common.Result(code,msg,null);
    }

    public static com.example.cto.common.Result error(){
        return new com.example.cto.common.Result(com.example.cto.common.Constants.CODE_500,"系统错误",null);
    }
}
