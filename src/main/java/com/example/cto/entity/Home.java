package com.example.cto.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author 张驰
 * @Date 31/3/2023 上午8:54
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class Home {
    private Integer income;//总收入
    private Integer touSuNumber;//退款

    private Integer empNumber;//员工数
    private Integer userNumber;//用户数
    private Integer orderNumber;//订单数量
    private String createTime;//创建时间
    private Integer count;//数量

    private List<String> month;
    private List<Integer> counts;
    public Home(String createTime, Integer count) {
        this.createTime = createTime;
        this.count = count;
    }


}
