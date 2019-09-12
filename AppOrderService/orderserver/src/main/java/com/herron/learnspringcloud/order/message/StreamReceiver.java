package com.herron.learnspringcloud.order.message;

import com.herron.learnspringcloud.order.DTO.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    public void process(Object message) {
        log.info("StreamReceiver: {}", message);
    }

    @StreamListener(StreamClient.INPUT)
    public void process2(OrderDTO msg) {
        log.info("StreamReceiver: {}", msg);
    }
}
