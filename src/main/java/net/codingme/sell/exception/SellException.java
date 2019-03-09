package net.codingme.sell.exception;

import lombok.Data;
import net.codingme.sell.enums.ResultEnum;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/31 17:21
 */
@Data
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SellException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }
}
