package com.herron.learnspringcloud.product.service;

import com.herron.learnspringcloud.product.DO.ProductInfo;
import com.herron.learnspringcloud.productcommon.DecreaseStockInput;
import com.herron.learnspringcloud.productcommon.ProductInfoOutput;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @param productIdList 商品id列表
     * @return 商品信息列表
     */
    List<ProductInfoOutput> getProductListById(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param decreaseStockInputList 要扣库存的商品id和数量
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
