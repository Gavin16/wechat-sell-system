package com.wechat.sell.repository;

import com.wechat.sell.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.repository
 * @Description:
 * @author: Minsky
 * @date: 2018/3/25 22:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    private static Logger logger = LoggerFactory.getLogger(ProductCategoryRepositoryTest.class);

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(1);
        logger.info(productCategory.toString());
    }

    @Test
    @Transactional
    protected void saveTest(){
        ProductCategory pc = new ProductCategory("热销榜",100000);
        ProductCategory back = repository.save(pc);
        Assert.assertNotNull(back);
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = repository.findOne(1);
        productCategory.setCategoryType(100001);

        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> inList = Arrays.asList(100001,100002,100003);

        List<ProductCategory> list = repository.findByCategoryTypeIn(inList);
        Assert.assertTrue(list.size()>1);
    }
}
