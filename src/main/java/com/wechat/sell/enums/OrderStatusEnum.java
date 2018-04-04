package com.wechat.sell.enums;

import lombok.Getter;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.enums
 * @Description:
 * @author: Minsky
 * @date: 2018/4/3 6:43
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISH(1,"已接单"),
    CANCEL(2,"已取消")
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
