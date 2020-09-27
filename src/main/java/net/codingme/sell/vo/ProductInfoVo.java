package net.codingme.sell.vo;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * <p>
 * 商品详情
 *
 * @Author niujinpeng
 * @Date 2019/1/29 21:33
 */
@Data
public class ProductInfoVo {

    @JSONField(name = "id")
    private String productId;

    @JSONField(name = "name")
    private String productName;

    @JSONField(name = "description")
    private String productDescription;

    @JSONField(name = "price")
    private BigDecimal productPrice;

    @JSONField(name = "icon")
    private String productIcon;
}
