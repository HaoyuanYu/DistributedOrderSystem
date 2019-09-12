package com.herron.learnspringcloud.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.herron.learnspringcloud.order.utils.JsonUtil;
import com.herron.learnspringcloud.productcommon.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_KEY_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process (String msg) {
        // 从消息队列获取消息
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>)JsonUtil.fromJson(msg,
                new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutputList);

        // 提取信息并存入redis
        if (!CollectionUtils.isEmpty(productInfoOutputList)) {
            for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
                stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_KEY_TEMPLATE,
                        productInfoOutput.getProductId()), String.valueOf(productInfoOutput.getProductStock()));
            }
        }
    }
}
