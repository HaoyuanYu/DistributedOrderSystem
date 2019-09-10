package com.herron.learnspringcloud.order.service.impl;

import com.herron.learnspringcloud.order.DO.OrderDetail;
import com.herron.learnspringcloud.order.DO.OrderMaster;
import com.herron.learnspringcloud.order.DTO.OrderDTO;
import com.herron.learnspringcloud.order.enums.OrderStatusEnum;
import com.herron.learnspringcloud.order.enums.PayStatusEnum;
import com.herron.learnspringcloud.order.repository.OrderDetailRepository;
import com.herron.learnspringcloud.order.repository.OrderMasterRepository;
import com.herron.learnspringcloud.order.service.OrderService;
import com.herron.learnspringcloud.order.utils.KeyUtils;
import com.herron.learnspringcloud.productclient.ProductClient;
import com.herron.learnspringcloud.productcommon.DecreaseStockInput;
import com.herron.learnspringcloud.productcommon.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /**
     * 1. 参数检验 (controller层完成)
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @Override
    @Transactional
    public String create(OrderDTO orderDTO) {
        String orderId = KeyUtils.genUniqueKey();
        orderDTO.setOrderId(orderId);

        // 查询商品信息
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = productClient.getProductListById(productIdList);

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        Map<String, ProductInfoOutput> productPriceMap = productInfoOutputList.stream().collect(Collectors.
                toMap(ProductInfoOutput::getProductId, Function.identity()));
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfoOutput productInfoOutput = productPriceMap.get(orderDetail.getProductId());
            BigDecimal price = productInfoOutput.getProductPrice();
            orderAmount = price.multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            BeanUtils.copyProperties(productInfoOutput, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
        }
        orderDTO.setOrderAmount(orderAmount);

        // 扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream().map(orderDetail ->
                new DecreaseStockInput(orderDetail.getProductId(), orderDetail.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            orderDetailRepository.save(orderDetail);
        }
        return orderId;
    }

    @Override
    public OrderDTO finish(String orderId) {
        // TODO: 2019/9/9  
        return null;
    }
}
