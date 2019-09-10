package com.herron.learnspringcloud.order.controller;

import com.herron.learnspringcloud.order.DTO.OrderDTO;
import com.herron.learnspringcloud.order.VO.ResultVO;
import com.herron.learnspringcloud.order.enums.ResultEnum;
import com.herron.learnspringcloud.order.exceptions.OrderException;
import com.herron.learnspringcloud.order.forms.OrderForm;
import com.herron.learnspringcloud.order.service.OrderService;
import com.herron.learnspringcloud.order.utils.ResultVOUtils;
import com.herron.learnspringcloud.order.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        OrderDTO orderDTO = Utils.orderForm2OrderDTO(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        String orderId = orderService.create(orderDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("orderId", orderId);
        return ResultVOUtils.success(resultMap);
    }
}
