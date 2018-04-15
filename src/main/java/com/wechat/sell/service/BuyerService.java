package com.wechat.sell.service;

import com.wechat.sell.dto.OrderDTO;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service
 * @Description:
 * @author: Minsky
 * @date: 2018/4/15 11:39
 */
public interface BuyerService {
    /**
     * 查询订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);
}
