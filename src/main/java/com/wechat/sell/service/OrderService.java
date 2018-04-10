package com.wechat.sell.service;

import com.wechat.sell.domain.OrderManager;
import com.wechat.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service
 * @Description:
 * @author: Eta
 * @date: 2018/4/9 20:08
 */
public interface OrderService {
    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 查询单个订单
    OrderDTO findOne(String orderId);

    // 查询订单列表
    Page<OrderDTO> findList(String customerOpenid,Pageable pageable);

    // 取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    // 完结订单
    OrderDTO finish(OrderDTO orderDTO);

    // 支付订单
    OrderDTO paid(OrderDTO orderDTO);
}
