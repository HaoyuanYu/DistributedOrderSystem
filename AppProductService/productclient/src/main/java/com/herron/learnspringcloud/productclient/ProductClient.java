package com.herron.learnspringcloud.productclient;

import com.herron.learnspringcloud.productcommon.DecreaseStockInput;
import com.herron.learnspringcloud.productcommon.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "AppProductService", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @PostMapping("/product/getProductListById")
    List<ProductInfoOutput> getProductListById(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    class ProductClientFallback implements ProductClient {

        @Override
        public List<ProductInfoOutput> getProductListById(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }
}
