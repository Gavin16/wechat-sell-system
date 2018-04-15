package com.wechat.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wechat.sell.domain.OrderDetail;
import com.wechat.sell.utils.serializer.Date2LongSerializer;
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
//@JsonInclude(JsonInclude.Include.NON_NULL)//如果返回某一字段结果为null,则不返回该值
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

    /**
     *  创建时间
     *  使用Date2LongSerializer 将date时间戳由毫秒级别改为秒级别
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 更新时间 */
//    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /***/
    private List<OrderDetail> orderDetailList;

}
