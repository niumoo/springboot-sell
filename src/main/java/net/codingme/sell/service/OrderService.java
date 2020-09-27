package net.codingme.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.codingme.sell.dto.OrderDTO;

/**
 * <p>
 * 订单
 *
 * @Author niujinpeng
 * @Date 2019/1/3116:43
 */
public interface OrderService {

    /**
     * 创建订单
     * 
     * @param orderDto
     * @return
     */
    OrderDTO create(OrderDTO orderDto);

    /**
     * 查询单个订单
     * 
     * @param orderId
     * @return
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     * 
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 查询订单列表
     *
     * @param pageable
     * @return
     */
    Page<OrderDTO> findList(Pageable pageable);

    /**
     * 取消订单
     * 
     * @param orderDto
     * @return
     */
    OrderDTO cancel(OrderDTO orderDto);

    /**
     * 完结订单
     * 
     * @param orderDto
     * @return
     */
    OrderDTO finish(OrderDTO orderDto);

    /**
     * 支付订单
     * 
     * @param orderDto
     * @return
     */
    OrderDTO paid(OrderDTO orderDto);
}
