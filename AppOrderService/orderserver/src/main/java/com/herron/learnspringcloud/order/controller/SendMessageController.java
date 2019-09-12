package com.herron.learnspringcloud.order.controller;

import com.herron.learnspringcloud.order.DTO.OrderDTO;
import com.herron.learnspringcloud.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        streamClient.output().send(MessageBuilder.withPayload("now " + new Date()).build());
    }

    @GetMapping("/sendObject")
    public void process2() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("gqers");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
