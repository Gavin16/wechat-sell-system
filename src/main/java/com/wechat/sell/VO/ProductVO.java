package com.wechat.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.VO
 * @Description:
 * @author: Minsky
 * @date: 2018/3/28 8:10
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private String categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
