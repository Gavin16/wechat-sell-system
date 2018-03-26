package com.wechat.sell.repository;

import com.wechat.sell.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Minsky
 * @date: 2018/3/26 21:57
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
