package net.codingme.sell.enums;

import lombok.*;

/**
 * <p>
 * HTTP 返回信息枚举类
 *
 * @Author niujinpeng
 * @Date 2019/1/30 10:48
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {

    /** 操作成功. */
    SUCCESS(0, "success"),
    /** 商品不存在 */
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    /** 商品库存不足 */
    PRODUCT_STOCK_ERROR(11, "库存不正确"),

    /** 订单不存在 */
    ORDER_NOT_EXIST(20, "订单不存在"),

    /** 商品类型不存在 */
    PRODUCT_CATEGORY_NOT_EXIST(31, "商品类型不存在");

    /** 状态码 */
    private Integer code;

    /** 返回信息 */
    private String message;
}
