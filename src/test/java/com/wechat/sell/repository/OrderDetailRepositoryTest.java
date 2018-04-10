package com.wechat.sell.repository;

import com.wechat.sell.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Eta
 * @date: 2018/4/7 20:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> detailList = repository.findByOrderId("237842");

        Assert.assertNotEquals(detailList.size(),0);
    }

    @Test
    public void saveOrderDetail(){
        OrderDetail detail = new OrderDetail();
        detail.setOrderId("237842");
        detail.setDetailId("7923692");
        detail.setProductName("小笼包");
        detail.setProductPrice(new BigDecimal(6.0));
        detail.setProductQuantity(2);
        detail.setProductIcon("shdfihsd");
        detail.setProductId("32489293");

        OrderDetail result = repository.save(detail);
        Assert.assertNotNull(result);
    }
}