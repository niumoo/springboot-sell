package net.codingme.sell.dto;

import lombok.Data;
import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.domain.OrderMaster;

import java.util.List;

/**
 * <p>
 * DTO 数据传输对象
 *
 * @Author niujinpeng
 * @Date 2019/1/31 16:46
 */
@Data
public class OrderDto extends OrderMaster {

    /** 订单详情 */
    private List<OrderDetail> orderDetailList;

}
