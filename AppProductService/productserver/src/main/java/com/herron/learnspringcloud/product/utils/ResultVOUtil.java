package com.herron.learnspringcloud.product.utils;


import com.herron.learnspringcloud.product.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
