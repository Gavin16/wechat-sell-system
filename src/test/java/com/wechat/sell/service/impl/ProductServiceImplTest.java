package com.wechat.sell.service.impl;

import com.wechat.sell.domain.ProductInfo;
import com.wechat.sell.enums.ProductStatusEnums;
import com.wechat.sell.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service.impl
 * @Description:
 * @author: Minsky
 * @date: 2018/3/27 22:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo pi = service.findOne("000001");
        Assert.assertNotNull(pi);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = service.findUpAll();
        Assert.assertTrue(productInfoList.size()>0);
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = service.findAll(request);
        System.out.println("total elements:" + productInfoPage.getTotalElements());
        Assert.assertTrue(productInfoPage.getSize() > 0);
    }

    @Test
    public void save() {
        ProductInfo pi = new ProductInfo();
        pi.setProductId("000002");
        pi.setProductIcon("/icon/12345");
        pi.setProductDescription("香醇可口的牛肉了解一下");
        pi.setProductStatus(ProductStatusEnums.ONSHELVES.getCode());
        pi.setProductStock(100);
        pi.setCategoryType(100004);
        pi.setProductName("红烧牛肉");
        pi.setProductPrice(23.00);

        service.save(pi);
    }
}