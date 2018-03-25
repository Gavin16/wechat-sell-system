package com.wechat.sell.repository;

import com.wechat.sell.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Minsky
 * @date: 2018/3/25 22:26
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> inList);
}
