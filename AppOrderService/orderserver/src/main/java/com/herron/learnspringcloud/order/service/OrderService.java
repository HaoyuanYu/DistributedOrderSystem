package com.herron.learnspringcloud.order.service;


import com.herron.learnspringcloud.order.DTO.OrderDTO;

public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO 订单数据传输对象
     * @return 订单数据传输对象
     */
    String create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家操作)
     *
     * @param orderId 订单数据传输对象
     * @return 订单数据传输对象
     */
    OrderDTO finish(String orderId);
}
