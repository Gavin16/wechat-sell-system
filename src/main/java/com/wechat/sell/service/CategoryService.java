package com.wechat.sell.service;

import com.wechat.sell.domain.ProductCategory;

import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service
 * @Description:
 * @author: Minsky
 * @date: 2018/3/26 21:21
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer>categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
