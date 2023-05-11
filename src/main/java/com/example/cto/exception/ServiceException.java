package com.example.cto.exception;

/**
 * @Author 张驰
 * @Date 2022/11/11 9:19
 * @Version 1.0
 */


import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
