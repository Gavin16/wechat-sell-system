package com.wechat.sell.controller;

import com.wechat.sell.VO.ProductInfoVO;
import com.wechat.sell.VO.ProductVO;
import com.wechat.sell.VO.ResultVO;
import com.wechat.sell.domain.ProductCategory;
import com.wechat.sell.domain.ProductInfo;
import com.wechat.sell.service.CategoryService;
import com.wechat.sell.service.impl.ProductServiceImpl;
import com.wechat.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.controller
 * @Description:
 * @author: Minsky
 * @date: 2018/3/28 7:47
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ResultVO list(){

        // 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        System.out.println("查询所有商品列表:"+productInfoList.toString());

        // 查询类目(一次性查询),遍历所有上架商品找出所有类目
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory>categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 把数据拼装成 返回结果需要的格式
        // 遍历类目,根据类目找出该类目下所有的商品
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory  category : categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());

            List<ProductInfoVO> infoVOList = new ArrayList<>();
            for(ProductInfo productInfo :productInfoList){
                if(productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO infoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,infoVO);
                    infoVOList.add(infoVO);
                }
            }
            productVO.setProductInfoVOList(infoVOList);
            productVOList.add(productVO);
        }

      return ResultVOUtil.success(productVOList);

    }
}
