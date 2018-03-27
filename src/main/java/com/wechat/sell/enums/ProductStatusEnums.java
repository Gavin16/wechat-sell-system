package com.wechat.sell.enums;

import lombok.Getter;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.enums
 * @Description:
 * @author: Minsky
 * @date: 2018/3/27 22:25
 */
@Getter
public enum ProductStatusEnums {
    ONSHELVES(0,"商品上架"),
    OFFSHELVES(1,"商品下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnums(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
