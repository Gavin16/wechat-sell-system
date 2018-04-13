package com.wechat.sell.enums;

import lombok.Getter;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.enums
 * @Description:
 * @author: Minsky
 * @date: 2018/4/3 6:47
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"待支付"),
    SUCCESS(1,"已支付")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
