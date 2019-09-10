package com.herron.learnspringcloud.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0, "新下单"),
    FINISHED(1, "完结"),
    CANCELED(2, "取消"),
    ;

    private int code;
    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
