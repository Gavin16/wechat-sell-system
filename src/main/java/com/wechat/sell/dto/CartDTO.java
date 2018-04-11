package com.wechat.sell.dto;

import lombok.Data;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.dto
 * @Description:
 * @author: Eta
 * @date: 2018/4/10 21:05
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId,Integer productQuantity){
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
