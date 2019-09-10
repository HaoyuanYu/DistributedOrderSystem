package com.herron.learnspringcloud.product.repository;

import java.util.List;

import com.herron.learnspringcloud.product.DO.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
