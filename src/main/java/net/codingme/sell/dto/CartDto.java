package net.codingme.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 购物车
 *
 * @Author niujinpeng
 * @Date 2019/1/31 22:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    /** 商品 id */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

}
