package com.wechat.sell.VO;

import lombok.Data;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.VO
 * @Description:
 * @author: Minsky
 * @date: 2018/3/28 7:51
 */
@Data
public class ResultVO<T> {
    private Integer code;

    private String msg;

    private T data;
}
