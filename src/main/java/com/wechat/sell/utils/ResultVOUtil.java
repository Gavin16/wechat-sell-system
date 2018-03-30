package com.wechat.sell.utils;

import com.wechat.sell.VO.ResultVO;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.utils
 * @Description:
 * @author: Eta
 * @date: 2018/3/30 7:51
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("ok");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
