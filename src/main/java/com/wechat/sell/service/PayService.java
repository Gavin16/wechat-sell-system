package com.wechat.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.wechat.sell.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * @Title: wechat-sell-system
 * @Package com.wechat.sell.service
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/13 11:06
 */
@Service
public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
