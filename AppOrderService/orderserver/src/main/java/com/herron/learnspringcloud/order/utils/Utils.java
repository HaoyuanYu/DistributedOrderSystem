package com.herron.learnspringcloud.order.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.herron.learnspringcloud.order.DO.OrderDetail;
import com.herron.learnspringcloud.order.DTO.OrderDTO;
import com.herron.learnspringcloud.order.enums.ResultEnum;
import com.herron.learnspringcloud.order.exceptions.OrderException;
import com.herron.learnspringcloud.order.forms.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Utils {

    public static OrderDTO orderForm2OrderDTO(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            log.error("json转换错误，传入数据为：{}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}

