package com.herron.learnspringcloud.product.controller;

import com.herron.learnspringcloud.product.DO.ProductCategory;
import com.herron.learnspringcloud.product.DO.ProductInfo;
import com.herron.learnspringcloud.product.VO.ProductInfoVO;
import com.herron.learnspringcloud.product.VO.ProductVO;
import com.herron.learnspringcloud.product.VO.ResultVO;
import com.herron.learnspringcloud.product.service.CategoryService;
import com.herron.learnspringcloud.product.service.ProductService;
import com.herron.learnspringcloud.product.utils.ResultVOUtil;
import com.herron.learnspringcloud.productcommon.DecreaseStockInput;
import com.herron.learnspringcloud.productcommon.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商品
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVO list() {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setProductInfoVOList(new ArrayList<>());
            productVOList.add(productVO);
        }
        Map<Integer, ProductVO> productVOMap = productVOList.stream()
                .collect(Collectors.toMap(ProductVO::getCategoryType, Function.identity()));
        for (ProductInfo productInfo : productInfoList) {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo, productInfoVO);
            ProductVO productVO = productVOMap.get(productInfo.getCategoryType());
            if (null != productVO) {
                productVO.getProductInfoVOList().add(productInfoVO);
            }
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表(给订单服务用的)
     *
     * @param productIdList 商品id列表
     * @return 商品信息列表
     */
    @PostMapping("/getProductListById")
    public List<ProductInfoOutput> getProductListById(@RequestBody List<String> productIdList) {
        log.info("获取商品列表");
        return productService.getProductListById(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }
}
