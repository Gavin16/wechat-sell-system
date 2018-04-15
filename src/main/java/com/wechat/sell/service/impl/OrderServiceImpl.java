package com.wechat.sell.service.impl;

import com.wechat.sell.converter.OrderManager2OrderDTO;
import com.wechat.sell.domain.OrderDetail;
import com.wechat.sell.domain.OrderManager;
import com.wechat.sell.domain.ProductInfo;
import com.wechat.sell.dto.CartDTO;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.OrderStatusEnum;
import com.wechat.sell.enums.PayStatusEnum;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.repository.OrderDetailRepository;
import com.wechat.sell.repository.OrderManagerRepository;
import com.wechat.sell.service.OrderService;
import com.wechat.sell.service.PayService;
import com.wechat.sell.service.ProductService;
import com.wechat.sell.utils.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.service.impl
 * @Description:
 * @author: Eta
 * @date: 2018/4/9 20:19
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderManagerRepository orderManagerRepository;

    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 创建订单
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1 查询商品数量和价格
        for(OrderDetail detail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(detail.getProductId());
            if(null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 计算订单总价钱 单个商品的价钱这里使用了前端传过来的值? -- 使用productInfo中的价格
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入库
            BeanUtils.copyProperties(productInfo,detail);//属性拷贝会将null也拷贝进去，因此这里先拷贝再设置
            detail.setOrderId(orderId);
            detail.setDetailId(KeyUtil.genUniqueKey());
            orderDetailRepository.save(detail);
        }

        //3 写入订单数据库 orderManager 和 orderD   etail
        orderDTO.setOrderId(orderId);
        OrderManager orderManager = new OrderManager();
        // 这里属性的拷贝会自动识别相同的属性进行拷贝？
        BeanUtils.copyProperties(orderDTO,orderManager);
//        orderManager.setOrderId(orderId);
        orderManager.setOrderAmount(orderAmount);
        orderManager.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderManager.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderManagerRepository.save(orderManager);

        //4 如果下单成功，需要扣除库存
        // 当并发量较大时,有可能多个用户同时查询出商品的库存量,然后再对商品进行口库存
        // 这样productService的decreaseStock方法无法判断是否超卖？？
        // 项目优化再做考虑
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderManager orderManager = orderManagerRepository.findOne(orderId);
        if(null == orderManager){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        // 集合为null 或者 非null集合为空
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
    }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderManager,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String customerOpenid, Pageable pageable) {
        Page<OrderManager>orderManagerPage = orderManagerRepository.findByCustomerOpenid(customerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderManager2OrderDTO.convert(orderManagerPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderManagerPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderManager orderManager = new OrderManager();

        // 判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())){
            logger.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 修改订单状态为取消状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderManager);

        OrderManager updateResult = orderManagerRepository.save(orderManager);
        if(null == updateResult){
            logger.error("【取消订单】更新失败，orderManager={}",orderManager);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 恢复库存,若订单中订单详情为空则抛出异常
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("【取消订单】订单中无商品详情,orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());

        productService.increaseStock(cartDTOList);
        // 如果已支付需要退款
        if(PayStatusEnum.SUCCESS.getCode().equals(orderDTO.getPayStatus())){
//            payService.refund(orderDTO);
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态 必须是未完成订单才能
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【完结订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderManager orderManager = new OrderManager();
        BeanUtils.copyProperties(orderDTO,orderManager);

        OrderManager updateResult = orderManagerRepository.save(orderManager);
        if(null == updateResult){
            logger.error("【完结订单】更新订单状态失败，orderManager={}",orderManager);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 推送微信模板消息
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【订单支付完成】订单状态不正确,orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            logger.error("【订单支付完成】订单支付状态不正确，orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderManager orderManager = new OrderManager();
        BeanUtils.copyProperties(orderDTO,orderManager);
        OrderManager updateResult = orderManagerRepository.save(orderManager);
        if(null == updateResult){
            logger.error("【订单支付完成】订单状态更新失败，orderManager={}",orderManager);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {

        Page<OrderManager> orderManagerPage = orderManagerRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderManager2OrderDTO.convert(orderManagerPage.getContent());

        return new PageImpl<>(orderDTOList,pageable,orderManagerPage.getTotalElements());
    }
}
