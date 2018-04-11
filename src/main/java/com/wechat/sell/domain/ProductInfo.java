package com.wechat.sell.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/3/26 21:51
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    /**
     *  ID
     */
    @Id
    private String productId;

    /**
     *  商品名称
     */
    private String productName;

    /**
     *  单价
     */
    private BigDecimal productPrice;

    /**
     *  商品库存
     */
    private Integer productStock;

    /**
     *  描述
     */
    private String productDescription;

    /**
     *  商品小图
     */
    private String productIcon;

    /**
     * 状态, 0 - 正常 1 - 下架
     */
    private Integer productStatus;

    /**
     * 类目编号
     */
    private Integer categoryType;


}
