package com.example.cto.entity;

import lombok.Data;

/**
 * @Author 张驰
 * @Date 2022/12/12 12:35
 * @Version 1.0
 */
@Data
public class AliPay {
    private String traceNo;
    private double  totalAmount;
    private String subject;
    private String alipayTraceNo;
}
