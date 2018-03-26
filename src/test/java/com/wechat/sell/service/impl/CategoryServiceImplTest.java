package com.wechat.sell.service.impl;

import com.wechat.sell.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service.impl
 * @Description:
 * @author: Minsky
 * @date: 2018/3/26 21:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories = categoryService.findAll();
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(Arrays.asList(100001,100002,100003));
        Assert.assertTrue(productCategories.size()>0);
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory pc = new ProductCategory("周末特惠",100005);
        ProductCategory saveResult = categoryService.save(pc);
        Assert.assertNotNull(saveResult);
    }
}