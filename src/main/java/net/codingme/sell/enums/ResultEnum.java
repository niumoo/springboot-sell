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
    SUCCESS(0, "success");

    /** 状态码 */
    private Integer code;

    /** 返回信息 */
    private String msg;
}
