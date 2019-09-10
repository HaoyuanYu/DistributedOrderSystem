package com.herron.learnspringcloud.product.service;

import com.herron.learnspringcloud.product.DO.ProductCategory;

import java.util.List;

/**
 * 类目
 * <p>
 * 2017-12-09 22:06
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
