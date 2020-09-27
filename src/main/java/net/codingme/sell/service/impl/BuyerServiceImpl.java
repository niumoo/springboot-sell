package net.codingme.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.dto.OrderDTO;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import net.codingme.sell.service.BuyerService;
import net.codingme.sell.service.OrderService;

/**
 * <p>
 * 买家
 *
 * @Author niujinpeng
 * @Date 2019/3/4 21:38
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return chcekOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO = chcekOrderOwner(openid, orderid);
        if (orderDTO == null) {
            log.error("【取消订单】查不到该订单，orderId={}", orderid);
            throw new SellException(ResultEnum.ORDER_MASTER_NOT_EXIST);
        }
        OrderDTO updateResult = orderService.cancel(orderDTO);
        return updateResult;
    }

    private OrderDTO chcekOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!openid.equalsIgnoreCase(orderDTO.getBuyerOpenid())) {
            log.error("【查询订单】订单的openid不一致,paramsOpenid={},resultOpenid={}", openid, orderDTO.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

}
