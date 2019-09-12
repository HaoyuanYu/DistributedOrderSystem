package com.herron.learnspringcloud.product.service;

import com.herron.learnspringcloud.product.DO.ProductCategory;

import java.util.List;

public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
