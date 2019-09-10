package com.herron.learnspringcloud.order.exceptions;

import com.herron.learnspringcloud.order.enums.ResultEnum;

public class OrderException extends RuntimeException {

    private int code;

    public OrderException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
