package com.wechat.sell.enums;

import lombok.Getter;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.enums
 * @Description:
 * @author: Eta
 * @date: 2018/4/10 7:57
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_ERROR(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
