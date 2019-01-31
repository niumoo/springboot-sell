package net.codingme.sell.service;

import net.codingme.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 订单
 *
 * @Author niujinpeng
 * @Date 2019/1/3116:43
 */
public interface OrderService {

    /** 创建订单 */
    OrderDto insert(OrderDto orderDto);

    /** 查询单个订单 */
    OrderDto findOne(String orderId);

    /** 查询订单列表 */
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单 */
    OrderDto cancel(OrderDto orderDto);

    /** 完结订单 */
    OrderDto finish(OrderDto orderDto);

    /** 支付订单 */
    OrderDto paid(OrderDto orderDto);
}
