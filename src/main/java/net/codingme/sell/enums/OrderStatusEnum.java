package net.codingme.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/31 16:12
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum implements CodeEnum {
    /** 订单状态，新下单 */
    NEW(0, "新订单"),
    /** 订单状态，完毕 */
    FINISHED(1, "完结"),
    /** 订单状态，取消 */
    CANCEL(2, "已取消");

    private Integer code;

    private String message;

}
