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
    /** 商品库存更新失败 */
    PRODUCT_STOCK_UPDATE_FIELD(12, "商品库存更新失败"),

    /** 订单不存在 */
    ORDER_MASTER_NOT_EXIST(20, "订单不存在"),
    /** 订单详情不存在 */
    ORDER_DETAIL_NOT_EXIST(21, "订单详情不存在"),
    /** 订单状态不正确 */
    ORDER_STATUS_ERROR(22, "订单状态不正确"),
    /** 订单更新失败 */
    ORDER_UPDATE_FIELD(23, "订单更新失败"),
    /** 订单中商品为空 */
    ORDER_DETAIL_EMPTY(24, "订单中商品为空"),

    /** 商品类型不存在 */
    PRODUCT_CATEGORY_NOT_EXIST(31, "商品类型不存在");

    /** 状态码 */
    private Integer code;

    /** 返回信息 */
    private String message;
}
