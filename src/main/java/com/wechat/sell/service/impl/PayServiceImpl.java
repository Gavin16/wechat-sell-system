package com.wechat.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.service.OrderService;
import com.wechat.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: wechat-sell-system
 * @Package com.wechat.sell.service.impl
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/13 11:15
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService{

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getCustomerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付,response={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 验证签名
        // 支付状态
        // 支付金额
        // 支付人和下单人是同一人
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】一步通知,payResponse={}",JsonUtil.toJson(payResponse));

        // 查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        // 判断订单是否存在
        if(null == orderDTO){
            log.error("【微信支付】异步通知,orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        // 判断金额是否一致
        if(!orderDTO.getOrderAmount().equals(payResponse.getOrderAmount())){
            // 支付金额不一致
            log.error("【微信支付】异步通知,订单金额不一致,orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信退款】request={}",JsonUtil.toJson(refundRequest));
        RefundResponse response = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}",JsonUtil.toJson(response));
        return response;
    }

}
