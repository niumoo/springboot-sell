package net.codingme.sell.utils;

import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.vo.ResultVo;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/30 11:08
 */
public class ResultVoUtil {

    public static ResultVo success(Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setMsg(ResultEnum.SUCCESS.getMessage());
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }

}
