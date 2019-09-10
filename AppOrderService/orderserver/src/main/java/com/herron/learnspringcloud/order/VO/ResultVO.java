package com.herron.learnspringcloud.order.VO;

import lombok.Data;

@Data
public class ResultVO<T> {

    private int code;

    private String msg;

    private T data;
}
