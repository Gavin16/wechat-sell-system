package com.wechat.sell.service.impl;

import com.wechat.sell.domain.OrderDetail;
import com.wechat.sell.domain.ProductInfo;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.repository.OrderDetailRepository;
import com.wechat.sell.service.OrderService;
import com.wechat.sell.service.ProductService;
import com.wechat.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service.impl
 * @Description:
 * @author: Eta
 * @date: 2018/4/9 20:19
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // 创建订单
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1 查询商品数量和价格
        for(OrderDetail detail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDTO.getOrderId());
            if(null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 计算订单总价钱
            orderAmount = detail.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入库
            detail.setOrderId(orderId);
            detail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo,detail);
            orderDetailRepository.save(detail);
        }

        //3 写入订单数据库 orderManager 和 orderDetail
        //4 如果下单成功，需要扣除库存
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String customerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
