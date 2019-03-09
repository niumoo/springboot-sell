package net.codingme.sell.exception;

import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.utils.ResultVoUtil;
import net.codingme.sell.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 统一的异常处理
 *
 * @Author niujinpeng
 * @Date 2019/1/7 14:26
 */

@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {

        if (e instanceof SellException) {
            SellException exception = (SellException) e;
            Integer code = exception.getCode();
            String message = exception.getMessage();
            return ResultVoUtil.error(code, message);
        }
        log.error("发现异常,excepution={}",e.getMessage());
        return ResultVoUtil.error(ResultEnum.UNKNOW_ERROR.getCode(),e.getMessage());
    }

}
