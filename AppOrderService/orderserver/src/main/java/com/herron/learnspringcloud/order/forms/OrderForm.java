package com.herron.learnspringcloud.order.forms;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "缺少姓名")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "缺少手机号")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "缺少地址")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "缺少openid")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
