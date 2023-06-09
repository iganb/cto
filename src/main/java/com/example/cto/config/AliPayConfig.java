package com.example.cto.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author 张驰
 * @Date 2022/12/12 12:32
 * @Version 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;


//    @PostConstruct
//    public void init() {
//        // 设置参数（全局只需设置一次）
//        Config config = new Config();
//        config.protocol = "https";
//        config.gatewayHost = "openapi.alipaydev.com";
//        config.signType = "RSA2";
//        config.appId = this.appId;
//        config.merchantPrivateKey = this.appPrivateKey;
//        config.alipayPublicKey = this.alipayPublicKey;
//        config.notifyUrl = this.notifyUrl;
//        Factory.setOptions(config);
//        System.out.println("=======支付宝SDK初始化成功=======");
//    }

}