package com.wechat.sell.utils;

import java.util.Random;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.utils
 * @Description:
 * @author: Eta
 * @date: 2018/4/10 8:09
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * @return 时间+随机数
     * 考虑一下高并发
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
