package com.wechat.sell.domain;

import com.wechat.sell.enums.OrderStatusEnum;
import com.wechat.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/4/3 6:32
 */
@Entity
@Data
@DynamicUpdate
public class OrderManager {

    /**
     * 订单ID
     */
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态
     */
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
