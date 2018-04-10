package com.wechat.sell.repository;

import com.wechat.sell.domain.OrderManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Eta
 * @date: 2018/4/7 20:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderManagerRepositoryTest {

    @Autowired
    private OrderManagerRepository repository;

    @Test
    public void findByCustomerOpenid() {
        PageRequest request = new PageRequest(0,5);
        Page<OrderManager> result = repository.findByCustomerOpenid("72364829342",request);
        Assert.assertNotEquals(result.getTotalElements(),0);
    }

    @Test
    public void saveTest(){
        OrderManager orderManager = new OrderManager();
        orderManager.setOrderId("12324");
        orderManager.setCustomerName("文章");
        orderManager.setCustomerOpenid("72364829342");
        orderManager.setCustomerAddress("宝安");
        orderManager.setCustomerPhone("18876349819");
        orderManager.setOrderAmount(new BigDecimal(17.8));

        OrderManager result = repository.save(orderManager);
        Assert.assertNotNull(result);
    }
}