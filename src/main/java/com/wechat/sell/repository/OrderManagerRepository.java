package com.wechat.sell.repository;

import com.wechat.sell.domain.OrderManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Eta
 * @date: 2018/4/7 20:11
 */
public interface OrderManagerRepository extends JpaRepository<OrderManager,String> {
    Page<OrderManager> findByCustomerOpenid(String openId, Pageable pageable);

}
