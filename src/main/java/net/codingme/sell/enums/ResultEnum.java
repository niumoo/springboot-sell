package net.codingme.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public enum ResultEnum implements CodeEnum {

    /** -1 未知错误 */
    UNKNOW_ERROR(-1, "未知错误"),
    /** 操作成功. */
    SUCCESS(0, "成功"),
    /** 该订单不属于当前用户 */
    ORDER_OWNER_ERROR(7, "该订单不属于当前用户"),
    /** 购物车不能为空 */
    CART_EMPTY(8, "购物车不能为空"),
    /** 参数检验失败 */
    PARAMS_ERROR(9, "参数校验失败"),

    /** 商品不存在 */
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    /** 商品库存不足 */
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    /** 商品库存更新失败 */
    PRODUCT_STOCK_UPDATE_FIELD(12, "商品库存更新失败"),
    /** 商品类型不存在 */
    PRODUCT_CATEGORY_NOT_EXIST(13, "商品类型不存在"),

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
    /** 订单支付状态不正确 */
    ORDER_PAY_STATUS_ERROR(25, "订单支付状态不正确"),
    /** 订单已取消 */
    ORDER_CANCEL_SUCCESS(26, "订单已取消");

    /** 状态码 */
    private Integer code;

    /** 返回信息 */
    private String message;
}
