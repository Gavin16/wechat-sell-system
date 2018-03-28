package com.wechat.sell.controller;

import com.wechat.sell.VO.ProductInfoVO;
import com.wechat.sell.VO.ProductVO;
import com.wechat.sell.VO.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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

    @RequestMapping("/list")
    public ResultVO list(){
        ResultVO rvo = new ResultVO();
        ProductVO pvo = new ProductVO();
        ProductInfoVO pivo = new ProductInfoVO();

        pvo.setProductInfoVOList(Arrays.asList(pivo));

        rvo.setCode(0);
        rvo.setMsg("ok");
        rvo.setData(Arrays.asList(pvo));

        return rvo;
    }
}
