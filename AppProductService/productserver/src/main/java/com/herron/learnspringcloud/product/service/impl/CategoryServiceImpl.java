package com.herron.learnspringcloud.product.service.impl;


import com.herron.learnspringcloud.product.DO.ProductCategory;
import com.herron.learnspringcloud.product.repository.ProductCategoryRepository;
import com.herron.learnspringcloud.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
