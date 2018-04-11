package com.wechat.sell.dto;

import com.wechat.sell.domain.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.dto
 * @Description:
 * @author: Eta
 * @date: 2018/4/9 20:12
 */
@Data
public class OrderDTO {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     *  买家姓名
     */
    private String customerName;

    /**
     *  买家电话
     */
    private String customerPhone;

    /**
     *  买家地址
     */
    private String customerAddress;

    /**
     *  买家openId
     */
    private String customerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /***/
    private List<OrderDetail> orderDetailList;

}
