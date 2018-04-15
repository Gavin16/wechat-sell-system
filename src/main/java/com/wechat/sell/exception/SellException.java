package com.wechat.sell.exception;

import com.wechat.sell.enums.ResultEnum;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.exception
 * @Description:
 * @author: Eta
 * @date: 2018/4/10 7:56
 */
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
