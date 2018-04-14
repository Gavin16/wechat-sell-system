package com.wechat.sell.service.impl;

import com.wechat.sell.domain.OrderDetail;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.OrderStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service.impl
 * @Description:
 * @author: Eta
 * @date: 2018/4/10 21:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Autowired
    private OrderServiceImpl orderService;

    private final String CUSTOMER_OPENID = "117118";

    private final String CUSTOMER_ORDER_ID = "1523403158467854567";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName("mingo");
        orderDTO.setCustomerAddress("南山区");
        orderDTO.setCustomerPhone("18218179305");
        orderDTO.setCustomerOpenid(CUSTOMER_OPENID);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail od1 = new OrderDetail();
        od1.setProductId("000001");
        od1.setProductQuantity(1);
        orderDetailList.add(od1);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("000002");
        od2.setProductQuantity(1);
        orderDetailList.add(od2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);

        logger.info("【创建订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne()throws Exception {
        OrderDTO orderDTO = orderService.findOne(CUSTOMER_ORDER_ID);
        logger.info("【查询定个订单】result={}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
//        List<OrderDTO> orderDTOList = orderService.findList();
        PageRequest request = new PageRequest(0,3);
        Page<OrderDTO> orderDTOPage = orderService.findList(CUSTOMER_OPENID,request);
        logger.info("【历史订单列表】为:{}",orderDTOPage.getContent());
        Assert.assertNotEquals(orderDTOPage.getTotalElements(),0);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(CUSTOMER_ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}