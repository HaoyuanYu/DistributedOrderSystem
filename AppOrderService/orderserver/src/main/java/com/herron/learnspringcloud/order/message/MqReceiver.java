package com.herron.learnspringcloud.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {

    @RabbitListener(queues = "myQueue")
    public void process(String msg) {
        log.info("MyReceiver: {}", msg);
    }
}
