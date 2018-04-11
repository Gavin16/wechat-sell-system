package com.wechat.sell.service.impl;

import com.wechat.sell.domain.OrderDetail;
import com.wechat.sell.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
    public void findOne() {

    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}