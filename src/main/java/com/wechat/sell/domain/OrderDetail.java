package com.wechat.sell.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/4/3 6:51
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单ID */
    private String orderId;

    /** 商品ID */
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品数量 */
    private Integer productQuantity;

    /** 商品图标 */
    private String productIcon;
}
