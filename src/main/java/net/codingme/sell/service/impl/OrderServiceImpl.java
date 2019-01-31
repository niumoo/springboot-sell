package net.codingme.sell.service.impl;

import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.domain.OrderMaster;
import net.codingme.sell.domain.ProductInfo;
import net.codingme.sell.dto.CartDto;
import net.codingme.sell.dto.OrderDto;
import net.codingme.sell.enums.OrderStatusEnum;
import net.codingme.sell.enums.PayStatusEnum;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import net.codingme.sell.repository.OrderDetailRepository;
import net.codingme.sell.repository.OrderMasterRepository;
import net.codingme.sell.service.OrderService;
import net.codingme.sell.service.ProductService;
import net.codingme.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/31 16:52
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDto insert(OrderDto orderDto) {

        // 1. 查询商品（数量，价格）
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 2. 计算总价
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 3. 写入订单数据库（ordermaster）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
            .map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
