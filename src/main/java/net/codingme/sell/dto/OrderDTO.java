package net.codingme.sell.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.enums.OrderStatusEnum;
import net.codingme.sell.enums.PayStatusEnum;
import net.codingme.sell.utils.EnumUtil;

/**
 * <p>
 * DTO 数据传输对象
 *
 * @Author niujinpeng
 * @Date 2019/1/31 16:46
 */
@Data
public class OrderDTO {

    /** 订单编号 */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态 , 默认为新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 订单详情 */
    private List<OrderDetail> orderDetailList;

    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
