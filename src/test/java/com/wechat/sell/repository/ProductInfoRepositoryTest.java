package com.wechat.sell.repository;

import com.wechat.sell.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Minsky
 * @date: 2018/3/26 22:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("000001");
        productInfo.setProductName("小炒肉");
        productInfo.setCategoryType(100001);
        productInfo.setProductStock(80);
        productInfo.setProductPrice(12.00);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("新鲜可口农家美味");
        productInfo.setProductIcon("/icon/10113");
        repository.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = repository.findByProductStatus(0);
        Assert.assertTrue(productInfos.size()>0);
    }
}