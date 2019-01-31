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
public enum PayStatusEnum {

    /** 支付状态，等待支付 */
    WAIT(0, "等待支付"),
    /** 支付状态，支付成功 */
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;
}
