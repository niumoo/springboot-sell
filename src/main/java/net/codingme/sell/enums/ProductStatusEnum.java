package net.codingme.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 * 
 * @Author niujinpeng
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum {

    /** 商品上架状态. */
    UP(0, "在架"),
    /** 商品下架状态. */
    DOWN(1, "下架");

    private Integer code;

    private String message;

}
