package net.codingme.sell.service;

import net.codingme.sell.dto.OrderDTO;

/**
 * <p>
 * 买家
 *
 * @Author niujinpeng
 * @Date 2019/3/421:32
 */
public interface BuyerService {

    /** 查询一个订单 */
    OrderDTO findOrderOne(String openid, String orderId);

    /** 取消一个订单 */
    OrderDTO cancelOrder(String openid, String orderid);
}
