package net.codingme.sell.exception;

import net.codingme.sell.enums.ResultEnum;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/31 17:21
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
