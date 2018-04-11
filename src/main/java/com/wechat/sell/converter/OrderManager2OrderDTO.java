package com.wechat.sell.converter;

import com.wechat.sell.domain.OrderManager;
import com.wechat.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.converter
 * @Description:
 * @author: Eta
 * @date: 2018/4/12 6:48
 */
public class OrderManager2OrderDTO {

    public static OrderDTO convert(OrderManager orderManager){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderManager,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderManager> orderManagerList){
        return orderManagerList.stream().map(o -> convert(o)).collect(Collectors.toList());
    }
}
