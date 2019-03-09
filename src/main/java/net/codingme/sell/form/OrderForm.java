package net.codingme.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 订单表单验证
 *
 * @Author niujinpeng
 * @Date 2019/3/2 15:54
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址不能为空")
    private String address;

    /**
     * 微信唯一标识id
     */
    @NotEmpty(message = "请使用微信操作")
    private String openid;

    /**
     * 购物车详情
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
