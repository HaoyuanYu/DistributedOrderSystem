package com.herron.learnspringcloud.order.utils;

import com.herron.learnspringcloud.order.VO.ResultVO;

public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setData(object);
        return resultVO;
    }
}
