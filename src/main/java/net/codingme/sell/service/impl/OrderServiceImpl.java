package net.codingme.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.converter.OrderMasterToOrderDTOConverter;
import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.domain.OrderMaster;
import net.codingme.sell.domain.ProductInfo;
import net.codingme.sell.dto.CartDTO;
import net.codingme.sell.dto.OrderDTO;
import net.codingme.sell.enums.OrderStatusEnum;
import net.codingme.sell.enums.PayStatusEnum;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import net.codingme.sell.repository.OrderDetailRepository;
import net.codingme.sell.repository.OrderMasterRepository;
import net.codingme.sell.service.OrderService;
import net.codingme.sell.service.ProductService;
import net.codingme.sell.utils.KeyUtil;
import org.junit.jupiter.api.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单管理
 *
 * @Author niujinpeng
 * @Date 2019/1/31 16:52
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    /**
     * 新增订单
     * 
     * @param orderDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO insert(OrderDTO orderDto) {

        // 1. 查询商品（数量，价格）
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 2. 计算总价
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 3. 写入订单数据库（ordermaster）
        orderDto.setOrderId(orderId);
        orderDto.setOrderAmount(orderAmount);
        orderDto.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDto.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        List<CartDTO> cartDtoList = orderDto.getOrderDetailList().stream()
            .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    /**
     * 查询单个订单
     * 
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        // 查询订单是否存在
        Optional<OrderMaster> orderOptional = orderMasterRepository.findById(orderId);
        OrderMaster orderMaster = orderOptional.orElseThrow(() -> new SellException(ResultEnum.ORDER_MASTER_NOT_EXIST));
        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        // 拼装结果对象
        OrderDTO orderDto = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    /**
     * 查询订单列表
     * 
     * @param buyerOpenid
     *            买家 openId
     * @param pageable
     *            分页信息
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMaster> orderMastList = orderMasterPage.getContent();
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMastList);
        long total = orderMasterPage.getTotalElements();
        return new PageImpl<OrderDTO>(orderDTOList, pageable, total);
    }

    /**
     * 取消订单
     * 
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDto) {
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            String orderId = orderDto.getOrderId();
            Integer status = orderDto.getOrderStatus();
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderId, status);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FIELD);
        }

        // 返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】订单中商品详情，orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDto.getOrderDetailList().stream()
            .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
            .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        // 如果已经支付，退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO
        }
        return orderDto;
    }

    /**
     * 完结订单
     * 
     * @param orderDto
     * @return1
     */
    @Override
    public OrderDTO finish(OrderDTO orderDto) {
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderDto={}，orderStatus={}", orderDto, orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // TODO 支付判断

        // 修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FIELD);
        }
        return orderDto;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDto) {
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确，orderDto={},orderStatus={}", orderDto, orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】订单支付状态不正确，orderDto={},orderPayStatus={}", orderDto, orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】订单支付状态更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FIELD);
        }
        return orderDto;
    }
}
